package common.basic.utils;

import common.basic.logs.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionUtil {

    public ReflectionUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static <T> List<Field> getAnnotatedField(Class<T> clazz, Class annotation) {
        List<Field> arrayField = getListFieldDeclaredRecursive(clazz);
        List<Field> listField = new ArrayList<Field>();
        for(Field field : arrayField){
            Annotation[] arrayAnnotation = field.getAnnotations();
            for(Annotation a : arrayAnnotation)
            {
                if(a.annotationType().equals(annotation))
                    listField.add(field);
            }
        }

        return listField;
    }

    public static <T, U extends Annotation> Field getAnnotatedFieldFirst(Class<T> clazz, Class<U> annotationClass) {
        List<Field> arrayField = getListFieldDeclaredRecursive(clazz);
        for(Field field : arrayField){
            final U annotation = field.getAnnotation(annotationClass);

            if (null != annotation)
                return field;
        }

        return null;
    }

    public static <T, U extends Annotation> String getAnnotatedFieldNameFirst(Class<T> clazz, Class<U> annotationClass) {
        Field annotatedField = ReflectionUtil.getAnnotatedFieldFirst(clazz, annotationClass);

        if (null == annotatedField)
            return "";

        return annotatedField.getName();
    }


    public static <T extends Annotation> Object getAnnotatedKeyFieldValue(Object object, Class<T> annotationClass) {
        Field annotatedField = ReflectionUtil.getAnnotatedFieldFirst(object.getClass(), annotationClass);

        if (null == annotatedField)
            return null;

        return ReflectionUtil.getValue(object, annotatedField);
    }

    private static <T extends Annotation> void setAnnotatedKeyFieldValue(Object object, Class<T> annotationClass, Object value) throws IllegalAccessException {
        Field annotatedField = ReflectionUtil.getAnnotatedFieldFirst(object.getClass(), annotationClass);

        if (null == annotatedField)
            return;

        setValue(object, annotatedField, value);
    }

    private static void setValue(Object object, Field field, Object value) throws IllegalAccessException {
        field.set(object, value);
    }


    public static <T> Map<String, Field> getMapField(Class<T> clazz) {

        List<Field> arrayField = getListFieldDeclaredRecursive(clazz);
        Map<String, Field> mapField = new HashMap<String, Field>(arrayField.size());

        for(Field field : arrayField)
        {
            mapField.put(field.getName(), field);
        }
        return mapField;
    }

    public static Object getValue(Object object, Field field) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            Logger.e(e);
            return null;
        }
    }



    public static <T> List<T> fromListMap(Class<T> clazz, List<Map<String, Object>> listMap) {
        return fromListMap(clazz, listMap, null);
    }

    public static <T, U extends Annotation> List<T> fromListMap(Class<T> clazz, List<Map<String, Object>> listMap, Class<U> annotationClass) {
        if(listMap == null)
            return null;

        List<T> list = new ArrayList<T>(listMap.size());
        for (Map<String, Object> map : listMap)
            list.add(fromMap(clazz, map, annotationClass));

        return list;
    }

    public static <T> T fromMap(Class<T> clazz, Map<String, Object> map) {
        return fromMap(clazz, map, null);
    }

    public static <T, U extends Annotation> T fromMap(Class<T> clazz, Map<String, Object> map, Class<U> annotationClass) {
        final T t;
        try {
            t = clazz.getConstructor(new Class<?>[0]).newInstance();
            for (String key : map.keySet())
            {
                final Object value = map.get(key);

                final Field field = getFieldDeclaredRecursive(clazz, key);
                if(Modifier.isTransient(field.getModifiers()))
                    continue;

                field.setAccessible(true);

                final Class<?> type = field.getType();
                if(type.isPrimitive() || type.equals(String.class) || type.equals(Date.class))
                {
                    field.set(t, value);
                }
                else
                {
                    if(type.isEnum())
                    {
                        //noinspection unchecked
                        field.set(t, EnumUtil.parse((Class<Enum>)type, (String)value));
                    }
                    else {
                        final Object o = type.newInstance();
                        if(annotationClass == null)
                            continue;

                        setAnnotatedKeyFieldValue(o, annotationClass, value);
                        field.set(t, o);
                    }
                }
            }
            return t;
        }
        catch (InstantiationException e) {
            Logger.e(e);
        }
        catch (IllegalAccessException e) {
            Logger.e(e);
        }
        catch (InvocationTargetException e) {
            Logger.e(e);
        }
        catch (NoSuchMethodException e) {
            Logger.e(e);
        }
        catch (NoSuchFieldException e) {
            Logger.e(e);
        }

        return null;
    }


    public static <T> Map<String, Object> toMap(T t) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            final Class<?> clazz = t.getClass();
            final List<Field> arrayField = getListFieldDeclaredRecursive(clazz);

            for (Field field : arrayField)
            {
                if(Modifier.isTransient(field.getModifiers()))
                    continue;

                field.setAccessible(true);
                map.put(field.getName(), field.get(t));
            }

            return map;
        }
        catch (IllegalAccessException e) {
            Logger.e(e);
        }

        return null;
    }


    public static <T> List<Field> getListFieldDeclaredRecursive(Class<T> clazz){
        List<Field> listField = new ArrayList<Field>();
        listField.addAll(Arrays.asList(clazz.getDeclaredFields()));
        final Class<? super T> classSuper = clazz.getSuperclass();
        if(null != classSuper)
        {
            listField.addAll(getListFieldDeclaredRecursive(classSuper));
        }

        return listField;
    }

    public static <T> Map<String, Field> getMapFieldDeclaredRecursive(Class<T> clazz) {
        final List<Field> listFieldDeclaredRecursive = getListFieldDeclaredRecursive(clazz);

        final Map<String, Field> mapField = new HashMap<String, Field>();
        for (Field field : listFieldDeclaredRecursive) {
            mapField.put(field.getName(), field);
        }
        return mapField;
    }

    public static <T> Field getFieldDeclaredRecursive(Class<T> clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException e) {
            final Class<? super T> classSuper = clazz.getSuperclass();
            if(null == classSuper)
            {
                throw e;
            }
            else
            {
                return getFieldDeclaredRecursive(classSuper, fieldName);
            }
        }
    }

}
