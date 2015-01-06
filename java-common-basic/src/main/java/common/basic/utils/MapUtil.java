package common.basic.utils;

import common.basic.interfaces.ICallbackTransform;
import common.basic.interfaces.ITransform;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
    public MapUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static <TKey, TValue> boolean equals(Map<TKey, TValue> map1, Map<TKey, TValue> map2) {
        if(map1.size() != map1.size())
            return false;

        for (TKey key : map1.keySet()) {
            if(!map2.containsKey(key))
                return false;

            final TValue value1 = map1.get(key);
            final TValue value2 = map1.get(key);
            if (!value1.equals(value2))
                return false;
        }

        return true;
    }

    public static <TKey, TValue> boolean isNullOrEmpty(Map<TKey, TValue> map) {
        if(null == map)
            return true;

        if(map.isEmpty())
            return true;

        return false;
    }

    public static void convertYNStringToBoolean(Map<String, Object> map, String... arrayKey) {
        for (String key : arrayKey){
             map.put(key, BooleanUtil.convertYN((String) map.get(key)));
        }
    }

    public static void convertBigDecimalToInt(Map<String, Object> map) {
        for (String key : map.keySet()) {
            final BigDecimal bigDecimal = Cast.as(map.get(key), BigDecimal.class);
            if(bigDecimal == null)
                continue;

            map.put(key, bigDecimal.intValue());
        }
    }

    public static <TKey, TValue> Map<TKey, TValue> transformKey(Map<TKey, TValue> map, ITransform<TKey> transform) {
        HashMap<TKey, TValue> mapResult = new HashMap<TKey, TValue>();

        for (TKey key : map.keySet()) {
            mapResult.put(transform.transform(key), map.get(key));
        }

        return mapResult;
    }


    public static <TKey, TValue> Map<TKey, TValue> transformFromList(List<TValue> list, ICallbackTransform<TValue, TKey> transform) {
        LinkedHashMap<TKey, TValue> mapResult = new LinkedHashMap<TKey, TValue>();

        for (TValue value : list) {
            mapResult.put(transform.transform(value), value);
        }

        return mapResult;
    }



    public static String getString(Map<String, Object> map, String key, String defaultValue) {
        if (!map.containsKey(key))
            return defaultValue;

        Object value = map.get(key);
        if (!(value instanceof String))
            return defaultValue;

        return value.toString();
    }

    public static String getString(Map<String, Object> map, String key) {
        return getString(map, key, "");
    }

    public static long getLong(Map<String, Object> map, String key, long defaultValue) {
        if (!map.containsKey(key))
            return defaultValue;

        Object value = map.get(key);
        if (value instanceof Long)
            return (Long) value;

        if (value instanceof Integer)
            return (Integer)value;

        return LongUtil.parseLong(value.toString(), defaultValue);
    }

    public static long getLong(Map<String, Object> map, String key) {
        return getLong(map, key, 0L);
    }
}
