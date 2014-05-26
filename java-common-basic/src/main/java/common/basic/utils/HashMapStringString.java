package common.basic.utils;

import java.util.HashMap;

public class HashMapStringString extends HashMap<String, String> {
    public HashMapStringString() {
    }

    public HashMapStringString(String key, String value) {
        and(key, value);
    }

    public HashMapStringString and(String key, String value) {
        put(key, value);
        return this;
    }
}
