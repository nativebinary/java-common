package common.basic.facades.jsons.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import common.basic.logs.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JacksonUtil {
    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static String stringify(Object o) throws JsonProcessingException {
        return createObjectMapper().writeValueAsString(o);
    }

    public static String stringifyCatches(Object o) {
        try {
            return stringify(o);
        }
        catch (JsonProcessingException e) {
            Logger.e(e);
            return null;
        }
    }


    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return createObjectMapper().readValue(json, clazz);
    }

    public static <T> T fromJson(JsonNode jsonNode, Class<T> clazz) throws JsonProcessingException {
        return createObjectMapper().treeToValue(jsonNode, clazz);
    }

    public static <T> T fromJson(InputStream json, Class<T> clazz) throws IOException {
        return createObjectMapper().readValue(json, clazz);
    }


    public static <T> T parseCatches(String json, Class<T> clazz) {
        try {
            return fromJson(json, clazz);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }

    public static <T> T fromJsonCatches(JsonNode jsonNode, Class<T> clazz) {
        try {
            return fromJson(jsonNode, clazz);
        }
        catch (JsonProcessingException e) {
            Logger.e(e);
            return null;
        }
    }

    public static <T> T parseCatches(InputStream json, Class<T> clazz) {
        try {
            return fromJson(json, clazz);
        } catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }


    public static JsonNode toJsonNode(String json) throws IOException {
        return createObjectMapper().readValue(json, JsonNode.class);
    }

    public static JsonNode toJsonNode(InputStream inputStream) throws IOException {
        return createObjectMapper().readValue(inputStream, JsonNode.class);
    }

    public static JsonNode toJsonNode(Object paramObject) {
        return createObjectMapper().valueToTree(paramObject);
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


    public static List<Object> toList(String json) throws IOException {
        return createObjectMapper().readValue(json, new TypeReference<List<Object>>() {
        });
    }

    public static <T> List<T> toList(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = createObjectMapper();
        CollectionType valueType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(json, valueType);
    }

    public static <T> List<T> toList(InputStream json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = createObjectMapper();
        CollectionType valueType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(json, valueType);
    }

    public static List<Object> toListCatches(String json) {
        try {
            return toList(json);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }


    public static <T> List<T> parseListCatches(String json, Class<T> clazz) {
        try {
            return toList(json, clazz);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }

    public static <T> List<T> parseListCatches(InputStream json, Class<T> clazz) {
        try {
            return toList(json, clazz);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }    }


    public static Map<String, Object> toMap(String json) throws IOException {
        return createObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {
        });
    }

    public static Map<String, Object> parseCatches(String json) {
        try {
            return toMap(json);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }


    public static List<Map<String, Object>> parseListCatches(String json) {
        try {
            return toListMap(json);
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }

    private static List<Map<String, Object>> toListMap(String json) throws IOException {
        return createObjectMapper().readValue(json, new TypeReference<List<Map<String, Object>>>() {
        });
    }

}


