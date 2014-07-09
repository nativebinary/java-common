package common.basic.jsons;

public class JsonUtil {


    static IJson json = new IJsonGson();
    // static IJson json = new IJsonJackson();

    public static String toJsonString(Object o) {
        return json.toJson(o);
    }
}
