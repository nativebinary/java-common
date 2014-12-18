package common.basic.facades.jsons;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IJsonEngine {
    String toJson(Object o);
    <T> T fromJson(String json, Class<T> clazz);
    List<Object> toList(String json);
    <T> List<T> toList(String json, Class<T> clazz);
    Map<String, Object> toMap(String json);
    List<Map<String, Object>> toListMap(String json);

    <T> T toT(String json, Class<T> clazz);
    <T> List<T> toListT(String json, Class<T> clazz);

    <T> T toT(InputStream json, Class<T> clazz);
    <T> List<T> toListT(InputStream json, Class<T> clazz);
}
