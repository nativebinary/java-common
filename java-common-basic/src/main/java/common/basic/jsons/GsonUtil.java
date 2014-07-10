package common.basic.jsons;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class GsonUtil {

    public GsonUtil() throws InstantiationException {
        throw new InstantiationException();
    }


    public static String toJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    public static void toJson(Object o, Appendable appendable) {
        Gson gson = new Gson();
        gson.toJson(o, appendable);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(InputStream inputStream, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(inputStream), clazz);
    }

    public static <T> T fromJson(Reader reader, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(reader, clazz);
    }

    public static <T> T fromJson(JsonObject jsonObject, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, clazz);
    }

    public static <T, U extends TypeToken<List<T>>> List<T> fromJsonArray(String json, U typeToken) {
        Gson gson = new Gson();
        return gson.fromJson(json, typeToken.getType());
    }

    public static JsonElement fromString(String body) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(body);
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



}
