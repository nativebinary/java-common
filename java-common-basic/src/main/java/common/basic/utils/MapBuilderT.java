package common.basic.utils;

import java.util.HashMap;

public class MapBuilderT<TKey, TValue> extends HashMap<TKey, TValue> {
    public MapBuilderT() {
    }

    public MapBuilderT(TKey key, TValue value) {
        and(key, value);
    }

    public MapBuilderT<TKey, TValue> and(TKey key, TValue value) {
        put(key, value);
        return this;
    }
}
