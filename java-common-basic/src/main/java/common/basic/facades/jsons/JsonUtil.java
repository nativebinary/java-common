package common.basic.facades.jsons;

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


    public static String toJson(Object o) {
        return jsonEngine.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return jsonEngine.fromJson(json, clazz);
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        return jsonEngine.toList(json, clazz);
    }

    public static List<Object> toList(String json) {
        return jsonEngine.toList(json);
    }

    public static Map<String, Object> toMap(String json) {
        return jsonEngine.toMap(json);
    }

    public static List<Map<String, Object>> toListMap(String json) {
        return JsonUtil.jsonEngine.toListMap(json);
    }
}
