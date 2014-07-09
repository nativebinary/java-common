package common.basic.jsons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import common.basic.logs.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JacksonUtil {
    public static String toJson(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    public static String toJsonCatches(Object o) {
        try {
            return toJson(o);
        }
        catch (JsonProcessingException e) {
            Logger.e(e);
            return null;
        }
    }



    private static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    public static <T> T fromJson(JsonNode jsonNode, Class<T> clazz) throws JsonProcessingException {
        return new ObjectMapper().treeToValue(jsonNode, clazz);
    }

    public static <T> T fromJsonCatches(String json, Class<T> clazz) {
        try {
            return fromJson(json, clazz);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }

    public static <A> A fromJsonCatches(JsonNode jsonNode, Class<A> clazz) {
        try {
            return fromJson(jsonNode, clazz);
        }
        catch (JsonProcessingException e) {
            Logger.e(e);
            return null;
        }
    }




    public static JsonNode toJsonNode(String json) throws IOException {
        return new ObjectMapper().readValue(json, JsonNode.class);
    }

    public static JsonNode toJsonNode(InputStream inputStream) throws IOException {
        return new ObjectMapper().readValue(inputStream, JsonNode.class);
    }

    public static JsonNode toJsonNode(Object paramObject)
    {
        return new ObjectMapper().valueToTree(paramObject);
    }


    public static JsonNode toJsonNodeCatches(String json) {
        try {
            return toJsonNode(json);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }

    public static JsonNode toJsonNodeCatches(InputStream inputStream) {
        try {
            return toJsonNode(inputStream);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }




    public static Map<String, Object> toMap(String json) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final TypeFactory typeFactory = mapper.getTypeFactory();
        final MapType mapType = typeFactory.constructMapType(Map.class, String.class, Object.class);
        return mapper.readValue(json, mapType);
    }

    public static Map<String, Object> toMapCatches(String json){
        try {
            return toMap(json);
        } catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }

}
