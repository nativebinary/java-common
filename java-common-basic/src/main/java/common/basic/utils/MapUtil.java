package common.basic.utils;

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
}
