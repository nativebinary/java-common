package common.basic.jsons;

import java.util.List;
import java.util.Map;

public class JsonUtil {


    static IJson json = new IJsonGson();
    // static IJson json = new IJsonJackson();

    public static String toJson(Object o) {
        return JsonUtil.json.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JsonUtil.json.fromJson(json, clazz);
    }

    public static List<Map<String, Object>> toListMap(String json) {
        return JsonUtil.json.toListMap(json);
    }
}
