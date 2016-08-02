package common.basic.utils;

import java.util.HashMap;

public class HashMapStringObject extends HashMap<String, Object> {
    public HashMapStringObject() {
    }

    public HashMapStringObject(String key, Object value) {
        and(key, value);
    }

    public HashMapStringObject and(String key, Object value) {
        put(key, value);
        return this;
    }
}
