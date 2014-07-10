package common.basic.jsons;

import java.util.List;
import java.util.Map;

public interface IJsonEngine {
    String toJson(Object o);
    <T> T fromJson(String json, Class<T> clazz);
    List<Map<String, Object>> toListMap(String json);
}
