package common.basic.xmls;

import java.util.HashMap;

public class Attributes extends HashMap<String, String> {
    public Attributes() {
    }

    public Attributes(String key, String value) {
        and(key, value);
    }

    public Attributes and(String key, String value) {
        put(key, value);
        return this;
    }
}
