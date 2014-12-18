package common.basic.facades.jsons.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import common.basic.logs.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonUtil {

    public GsonUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .serializeNulls()
//              TODO  .setExclusionStrategies(new ExclusionStrategyWithExcludeGson())
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }


    public static String toJson(Object o) {
        return createGson().toJson(o);
    }

    public static void toJson(Object o, Appendable appendable) {
        createGson().toJson(o, appendable);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return createGson().fromJson(json, clazz);
    }

    public static <T> T fromJson(InputStream inputStream, Class<T> clazz) {
        return createGson().fromJson(new InputStreamReader(inputStream), clazz);
    }

    public static <T> T fromJson(Reader reader, Class<T> clazz) {
        return createGson().fromJson(reader, clazz);
    }

    public static <T> T fromJson(JsonObject jsonObject, Class<T> clazz) {
        return createGson().fromJson(jsonObject, clazz);
    }

    @Deprecated // use toList()
    public static <T, U extends TypeToken<List<T>>> List<T> fromJsonArray(String json, U typeToken) {
        Logger.e("Deprecated, use toList().");
        return createGson().fromJson(json, typeToken.getType());
    }

    public static <T, U extends TypeToken<List<T>>> List<T> toList(String json, U typeToken) {
        return createGson().fromJson(json, typeToken.getType());
    }

    public static JsonElement fromString(String body) {
        return new JsonParser().parse(body);
    }

    public static JsonElement get(JsonElement jsonElement, String memberName) {
        JsonElement element = jsonElement.getAsJsonObject().get(memberName);

        if (null == element || element.isJsonNull())
            return null;

        return element;
    }


    public static String get(JsonElement jsonElement, String memberName, String sDefault) {
        final JsonElement jsonElementChild = get(jsonElement, memberName);
        if(null == jsonElementChild)
            return sDefault;

        return jsonElementChild.getAsString();
    }

    public static double get(JsonElement jsonElement, String memberName, double dDefault) {
        final JsonElement jsonElementChild = get(jsonElement, memberName);
        if(null == jsonElementChild)
            return dDefault;

        return jsonElementChild.getAsDouble();
    }

    public static long get(JsonElement jsonElement, String memberName, long lDefault) {
        final JsonElement jsonElementChild = get(jsonElement, memberName);
        if(null == jsonElementChild)
            return lDefault;

        return jsonElementChild.getAsLong();
    }


    public static Map<String, Object> fromJsonMap(String json) {

        return createGson().fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
    }

    public static <T> List<T> toList(InputStream json, TypeToken<List<T>> typeToken) {
        return null;
    }
}
