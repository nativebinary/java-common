package common.basic.jsons;

import java.util.List;
import java.util.Map;

public class JsonEngineJackson implements IJsonEngine {
    @Override
    public String toJson(Object o) {
        return JacksonUtil.toJsonCatches(o);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return JacksonUtil.fromJsonCatches(json, clazz);
    }

    @Override
    public List<Map<String, Object>> toListMap(String json) {
        return JacksonUtil.toListMapCatches(json);
    }
}
