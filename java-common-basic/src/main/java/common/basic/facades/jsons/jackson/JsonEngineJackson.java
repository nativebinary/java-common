package common.basic.facades.jsons.jackson;

import common.basic.facades.jsons.IJsonEngine;

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
    public List<Object> toList(String json) {
        return JacksonUtil.toListCatches(json);
    }

    @Override
    public <T> List<T> toList(String json, Class<T> clazz) {
        return JacksonUtil.toListCatches(json, clazz);
    }

    @Override
    public Map<String, Object> toMap(String json) {
        return JacksonUtil.toMapCatches(json);
    }

    @Override
    public List<Map<String, Object>> toListMap(String json) {
        return JacksonUtil.toListMapCatches(json);
    }
}
