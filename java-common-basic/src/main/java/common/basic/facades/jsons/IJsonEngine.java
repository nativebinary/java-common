package common.basic.facades.jsons;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IJsonEngine {

    String stringify(Object o);

    <T> T parse(String json, Class<T> clazz);
    <T> T parse(String json, JsonTypeT<T> type);
    <T> T parse(InputStream inputStream, Class<T> clazz);
    <T> T parse(InputStream inputStream, JsonTypeT<T> type);
    Map<String, Object> parse(String json);

    <T> List<T> parseList(String json, Class<T> clazz);
    <T> List<T> parseList(InputStream inputStream, Class<T> clazz);
    List<Map<String, Object>> parseList(String json);
}

