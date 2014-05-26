package common.basic.utils;

import java.util.Map;

public class Pair<TKey, TValue> implements Map.Entry<TKey, TValue> {
    final TKey key;
    TValue value;

    public Pair(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public TKey getKey() {
        return key;
    }

    @Override
    public TValue getValue() {
        return value;
    }

    @Override
    public TValue setValue(TValue value) {
        return this.value = value;
    }
}
