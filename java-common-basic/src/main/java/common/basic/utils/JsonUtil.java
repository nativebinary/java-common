package common.basic.utils;

public class JsonUtil {

    public static String toJsonString(Object o) {
        return GsonUtil.toJson(o);
    }
}
