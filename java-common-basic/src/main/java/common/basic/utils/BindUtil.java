package common.basic.utils;

import common.basic.logs.Logger;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

public class BindUtil {
    // TODO: move this method to Reflection Util
    public static void bind(Object object, Map<String, String[]> mapParam) {
        final Field[] arrayField = object.getClass().getDeclaredFields();
        for (Field field : arrayField) {
            final String[] arrayValues = mapParam.get(field.getName());

            if (null == arrayValues || 0 == arrayValues.length)
                continue;

            try {
                final Class<?> type = field.getType();
                final String value = arrayValues[0];

                if (type.equals(long.class) || type.equals(Long.class)) {
                    field.setLong(object, LongUtil.parseLong(value, 0));
                } else if (type.equals(int.class) || type.equals(Integer.class)) {
                    field.setInt(object, IntUtil.parseInt(value, 0));
                } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
                    field.set(object, BooleanUtil.convertYN(value));
                } else if (type.equals(Date.class)) {
                    field.set(object, DateUtil.parse(value));
                } else {
                    field.set(object, value);
                }
            } catch (Exception e) {
                Logger.e(e);
            }
        }

    }
}
