package common.play1.utils;

import common.basic.utils.ReflectionUtil;
import siena.Id;

public class SienaUtil {

    public static String getAnnotatedKeyFieldName(Class clazz)  {
        final Class<Id> annotationClass = Id.class;
        return ReflectionUtil.getAnnotatedKeyFieldName(clazz, annotationClass);
    }

    public static Object getAnnotatedKeyFieldValue(Object object)  {
        final Class<Id> annotationClass = Id.class;
        return ReflectionUtil.getAnnotatedKeyFieldValue(object, annotationClass);
    }

}
