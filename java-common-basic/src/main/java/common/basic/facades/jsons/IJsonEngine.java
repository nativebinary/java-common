package common.basic.facades.jsons;

import java.util.List;
import java.util.Map;

public interface IJsonEngine {
    String toJson(Object o);
    <T> T fromJson(String json, Class<T> clazz);
    List<Object> toList(String json);
    <T> List<T> toList(String json, Class<T> clazz);
    Map<String, Object> toMap(String json);
    List<Map<String, Object>> toListMap(String json);

}
