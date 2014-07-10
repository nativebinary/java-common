package common.basic.jsons;

import java.util.List;
import java.util.Map;

public class JsonUtil {


    static IJsonEngine jsonEngine = new JsonEngineGson();

    public JsonUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static IJsonEngine setJsonEngine(IJsonEngine jsonEngine) {
        IJsonEngine old = JsonUtil.jsonEngine;
        JsonUtil.jsonEngine = jsonEngine;
        return old;
    }


    public static String toJson(Object o) {
        return JsonUtil.jsonEngine.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JsonUtil.jsonEngine.fromJson(json, clazz);
    }

    public static List<Map<String, Object>> toListMap(String json) {
        return JsonUtil.jsonEngine.toListMap(json);
    }
}
