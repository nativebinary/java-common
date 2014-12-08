package common.basic.facades.jsons;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    static IJsonEngine jsonEngine;

    public JsonUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static IJsonEngine setJsonEngine(IJsonEngine jsonEngine) {
        IJsonEngine old = JsonUtil.jsonEngine;
        JsonUtil.jsonEngine = jsonEngine;
        return old;
    }


    public static String stringify(Object o) {
        return jsonEngine.stringify(o);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        return jsonEngine.parse(json, clazz);
    }

    public static <T> T parse(InputStream inputStream, Class<T> clazz) {
        return jsonEngine.parse(inputStream, clazz);
    }

    public static Map<String, Object> parse(String json) {
        return jsonEngine.parse(json);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return jsonEngine.parseList(json, clazz);
    }

    public static <T> List<T> parseList(InputStream inputStream, Class<T> clazz) {
        return jsonEngine.parseList(inputStream, clazz);
    }

    public static List<Map<String, Object>> parseList(String json) {
        return jsonEngine.parseList(json);
    }
}
