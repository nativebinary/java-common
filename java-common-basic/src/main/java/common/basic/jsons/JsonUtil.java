package common.basic.jsons;

public class JsonUtil {


    static IJson json = new IJsonGson();
    // static IJson json = new IJsonJackson();

    public static String toJson(Object o) {
        return JsonUtil.json.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JsonUtil.json.fromJson(json, clazz);
    }
}
