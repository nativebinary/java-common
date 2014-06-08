package common.basic.utils;

import java.math.BigDecimal;
import java.util.Map;

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
}
