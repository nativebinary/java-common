package common.basic.utils;

import common.basic.interfaces.ICallbackTransform;
import common.basic.interfaces.IPredicator;
import common.basic.logs.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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


    public static <T extends Annotation> Object getAnnotatedFieldValueFirst(Object object, Class<T> annotationClass) {
        Field annotatedField = ReflectionUtil.getAnnotatedFieldFirst(object.getClass(), annotationClass);

        if (null == annotatedField)
            return null;

        return ReflectionUtil.getValue(object, annotatedField);
    }

    public static <T extends Annotation> void setAnnotatedKeyFieldValue(Object object, Class<T> annotationClass, Object value) throws IllegalAccessException {
        Field annotatedField = ReflectionUtil.getAnnotatedFieldFirst(object.getClass(), annotationClass);

        if (null == annotatedField)
            return;

        setValue(object, annotatedField, value);
    }

    public static void setValue(Object object, Field field, Object value) throws IllegalAccessException {
        field.set(object, value);
    }

    public static <T, U extends Annotation> void setFieldValue(T instance, Field field, Object value, Class<U> annotationClass) throws IllegalAccessException, InstantiationException {
        field.setAccessible(true);

        final Class<?> type = field.getType();

        if(type.isPrimitive())
        {
            if (value instanceof String) {
                String stringValue = value.toString();

                if ("int".equals(type.getName())) {
                    field.setInt(instance, IntUtil.parseInt(stringValue, 0));
                } else if ("long".equals(type.getName())) {
                    field.setLong(instance, LongUtil.parseLong(stringValue, 0));
                } else if ("float".equals(type.getName())) {
                    field.setFloat(instance, Float.parseFloat(stringValue));
                } else if ("double".equals(type.getName())) {
                    field.setDouble(instance, Double.parseDouble(stringValue));
                } else if ("boolean".equals(type.getName())) {
                    field.setBoolean(instance, BooleanUtil.parse(stringValue));
                } else if ("byte".equals(type.getName())) {
                    field.setByte(instance, Byte.parseByte(stringValue));
                } else if ("short".equals(type.getName())) {
                    field.setShort(instance, Short.parseShort(stringValue));
                } else if ("char".equals(type.getName())) {
                    field.setChar(instance, stringValue.charAt(0));
                } else {
                    field.set(instance, stringValue);
                }
            } else {
                if ("int".equals(type.getName()) && (value instanceof Long)) {
                    field.setInt(instance, ((Long) value).intValue());
                } else if ("float".equals(type.getName()) && (value instanceof Double)) {
                    field.setFloat(instance, ((Double) value).floatValue());
                } else if ("boolean".equals(type.getName())) {
                    field.setBoolean(instance, BooleanUtil.parse(value.toString()));
                } else {
                    field.set(instance, value);
                }
            }

        } else if(type.equals(String.class) || type.equals(Date.class)) {
            field.set(instance, value);
        } else if (type == java.util.List.class && value instanceof List) {
            ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
            Class<?> genericClass = (Class<?>) stringListType.getActualTypeArguments()[0];

            List<?> objects = fromListMapByClass(genericClass, (List<Map<String, Object>>)value);

            field.set(instance, objects);
        } else if(type.isEnum()) {
            //noinspection unchecked
            field.set(instance, EnumUtil.parse((Class<Enum>) type, (String) value));
        } else {
            if(annotationClass == null)
                return;

            final Object o = type.newInstance();
            setAnnotatedKeyFieldValue(o, annotationClass, value);
            field.set(instance, o);
        }
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

    public static <T> List<T> fromListMapByClass(Class<T> clazz, List<Map<String, Object>> listMap) {
        return fromListMapByClass(clazz, listMap, null);
    }

    public static <T, U extends Annotation> List<T> fromListMapByClass(Class<T> clazz, List<Map<String, Object>> listMap, Class<U> annotationClass) {
        if(listMap == null)
            return null;

        List<T> list = new ArrayList<T>(listMap.size());
        for (Map<String, Object> map : listMap)
            list.add(fromMapByClass(clazz, map, annotationClass));

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


                setFieldValue(t, field, value, annotationClass);
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


    public static <T> T fromMapByClass(Class<T> clazz, Map<String, Object> map) {
        return fromMapByClass(clazz, map, null);
    }

    public static <T, U extends Annotation> T fromMapByClass(Class<T> clazz, Map<String, Object> map, Class<U> annotationClass) {
        final T t;
        try {
            t = clazz.getConstructor(new Class<?>[0]).newInstance();

            List<Field> listFieldDeclaredRecursive = getListFieldDeclaredRecursive(clazz);

            for (Field field : listFieldDeclaredRecursive) {
                String fieldName = field.getName();

                Object value = map.get(fieldName);
                if (null == value)
                    continue;

                if(Modifier.isTransient(field.getModifiers()))
                    continue;

                setFieldValue(t, field, value, annotationClass);
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


    public static <TLhs, TRhs> void assignAnnotatedMemberVariable(List<TLhs> listLhs, List<TRhs> listRhs, Class classAnnotation) {
        if(listLhs.size() == 0) {
            Logger.e("listLhs.size() == 0");
            return;
        }

        if(listRhs.size() == 0) {
            Logger.e("listRhs.size() == 0");
            return;
        }

        final TLhs lhs0 = listLhs.get(0);
        final TRhs rhs0 = listRhs.get(0);
        final Class<?> classLhs = lhs0.getClass();
        final Class<?> classRhs = rhs0.getClass();

        final List<Field> listTarget = ListUtil.findAll(getListFieldDeclaredRecursive(classLhs), new IPredicator<Field>() {
            @Override
            public boolean predicate(Field field) {
                return field.getType().equals(classRhs);
            }
        });

        Map<Object, TRhs> map = new HashMap<Object, TRhs>();
        for (TRhs rhs : listRhs) {
            map.put(getAnnotatedFieldValueFirst(rhs, classAnnotation), rhs);
        }


        for (Field field : listTarget) {
            for (TLhs lhs : listLhs) {
                final Object memberVar = getValue(lhs, field);
                final Object annotatedFieldValueFirst = getAnnotatedFieldValueFirst(memberVar, classAnnotation);

                try {
                    final TRhs value = map.get(annotatedFieldValueFirst);
                    if(value == null)
                        continue;

                    setValue(lhs, field, value);
                }
                catch (IllegalAccessException e) {
                    Logger.e(e);
                }
            }
        }
    }


    private static final String TYPE_CLASS_NAME_PREFIX = "class ";
    private static final String TYPE_INTERFACE_NAME_PREFIX = "interface ";

    public static String getClassName(Type type) {
        if (type==null) {
            return "";
        }
        String className = type.toString();
        if (className.startsWith(TYPE_CLASS_NAME_PREFIX)) {
            className = className.substring(TYPE_CLASS_NAME_PREFIX.length());
        } else if (className.startsWith(TYPE_INTERFACE_NAME_PREFIX)) {
            className = className.substring(TYPE_INTERFACE_NAME_PREFIX.length());
        }
        return className;
    }

    public static Class<?> getClass(Type type)
            throws ClassNotFoundException {
        String className = getClassName(type);
        if (className==null || className.isEmpty()) {
            return null;
        }
        return Class.forName(className);
    }


    ////////////////////////////////



    public static <T, U extends Annotation> void setFieldByStringArray(T instance, Field field, String[] arrayString, Class<U> annotationClass) throws IllegalAccessException, InstantiationException {

        if (null == arrayString || 0 == arrayString.length)
            return;

        field.setAccessible(true);

        final Class<?> type = field.getType();

        if(type.isPrimitive())
        {
            String stringValue = arrayString[0];

            if (int.class.equals(type)) {
                field.setInt(instance, IntUtil.parseInt(stringValue, 0));
            } else if (long.class.equals(type)) {
                field.setLong(instance, LongUtil.parseLong(stringValue, 0));
            } else if (float.class.equals(type)) {
                field.setFloat(instance, Float.parseFloat(stringValue));
            } else if (double.class.equals(type)) {
                field.setDouble(instance, Double.parseDouble(stringValue));
            } else if (boolean.class.equals(type)) {
                field.setBoolean(instance, BooleanUtil.parse(stringValue));
            } else if (byte.class.equals(type)) {
                field.setByte(instance, Byte.parseByte(stringValue));
            } else if (short.class.equals(type)) {
                field.setShort(instance, Short.parseShort(stringValue));
            } else if (char.class.equals(type)) {
                field.setChar(instance, stringValue.charAt(0));
            } else {
                field.set(instance, stringValue);
            }
        } else if(type.equals(String.class)) {
            String stringValue = arrayString[0];
            field.set(instance, stringValue);
        } else if(type.equals(Date.class)) {
            String stringValue = arrayString[0];
            Date parse = DateUtil.yyyyMMddHHmmss(stringValue, null);
            if (null != parse)
                field.set(instance, parse);

        } else if (type.equals(java.util.List.class)) {
            ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
            Class<?> genericClass = (Class<?>) stringListType.getActualTypeArguments()[0];

            if (genericClass.isEnum()) {
                List<Enum> objects = new ArrayList<Enum>();
                for (String singleValue : arrayString) {
                    Enum parse = EnumUtil.parse((Class<? extends Enum>) genericClass, singleValue);
                    objects.add(parse);
                }
                field.set(instance, objects);
            } else if (genericClass.equals(String.class)){
                List<String> objects = new ArrayList<String>();
                Collections.addAll(objects, arrayString);
                field.set(instance, objects);
            } else {
                if (Integer.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Integer>() {
                        @Override
                        public Integer transform(String s) {
                            return IntUtil.parseInt(s, 0);
                        }
                    }));
                } else if (Long.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Long>() {
                        @Override
                        public Long transform(String s) {
                            return LongUtil.parseLong(s, 0);
                        }
                    }));
                } else if (Float.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Float>() {
                        @Override
                        public Float transform(String s) {
                            return Float.parseFloat(s);
                        }
                    }));
                } else if (Double.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Double>() {
                        @Override
                        public Double transform(String s) {
                            return Double.parseDouble(s);
                        }
                    }));
                } else if (Boolean.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Boolean>() {
                        @Override
                        public Boolean transform(String s) {
                            return BooleanUtil.parse(s);
                        }
                    }));
                } else if (Byte.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Byte>() {
                        @Override
                        public Byte transform(String s) {
                            return Byte.parseByte(s);
                        }
                    }));
                } else if (Short.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Short>() {
                        @Override
                        public Short transform(String s) {
                            return Short.parseShort(s);
                        }
                    }));
                } else if (Character.class.equals(type)) {
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Character>() {
                        @Override
                        public Character transform(String s) {
                            if (StringUtil.isNullOrEmpty(s))
                                return null;

                            return s.charAt(0);
                        }
                    }));
                } else {
                    Logger.e(field, arrayString);
                    field.set(instance, ListUtil.transform(arrayString, new ICallbackTransform<String, Object>() {
                        @Override
                        public Object transform(String s) {
                            return s;
                        }
                    }));
                }


            }
        } else if(type.isEnum()) {
            //noinspection unchecked
            String stringValue = arrayString[0];
            field.set(instance, EnumUtil.parse((Class<? extends Enum>) type, stringValue));
        } else {
            if(annotationClass == null)
                return;

            final Object o = type.newInstance();
            String stringValue = arrayString[0];
            setAnnotatedKeyFieldValue(o, annotationClass, stringValue);
            field.set(instance, o);
        }
    }

    public static <T, U extends Annotation> T bindFromStringStringArray(Class<T> clazz, Map<String, String[]> map, Class<U> annotationClass) {
        final T t;
        try {
            t = clazz.getConstructor(new Class<?>[0]).newInstance();

            List<Field> listFieldDeclaredRecursive = getListFieldDeclaredRecursive(clazz);

            for (Field field : listFieldDeclaredRecursive) {
                String fieldName = field.getName();

                String[] value = map.get(fieldName);
                if (null == value)
                    continue;

                if(Modifier.isTransient(field.getModifiers()))
                    continue;

                setFieldByStringArray(t, field, value, annotationClass);
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

        return null;
    }

    public static <T> T bindFromStringStringArray(Class<T> clazz, Map<String, String[]> map) {
        return bindFromStringStringArray(clazz, map, null);
    }


    public static <T> void invokeIfExists(T t, String method) {
        final Class<?> clazz = t.getClass();
        try {
            Method declaredMethod = clazz.getDeclaredMethod(method);
            declaredMethod.invoke(t);
        } catch (NoSuchMethodException e) {
            // Do Nothing
        } catch (InvocationTargetException e) {
            Logger.i(e);
        } catch (IllegalAccessException e) {
            Logger.i(e);
        }

    }
}
