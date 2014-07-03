package common.basic.utils;

public class JsonUtil {
    public static interface IJson {
        String toJson(Object o);
    }

    static IJson json = new IJson() {
        @Override
        public String toJson(Object o) {
            return GsonUtil.toJson(o);
//            return Json.stringify(Json.toJson(o));
        }
    };

    public static String toJsonString(Object o) {
        return json.toJson(o);
    }
}
