package common.basic.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtil {
    MapUtil() throws InstantiationException {
        throw new InstantiationException();
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

    public static interface ITransform<T> {
        T transform(T t);
    }

    public static <TKey, TValue> Map<TKey, TValue> transformKey(Map<TKey, TValue> map, ITransform<TKey> transform) {
        HashMap<TKey, TValue> mapResult = new HashMap<TKey, TValue>();

        final Set<TKey> setKey = map.keySet();
        for (TKey key : setKey) {
            mapResult.put(transform.transform(key), map.get(key));
        }

        return mapResult;
    }

}
