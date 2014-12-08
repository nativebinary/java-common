package common.basic.facades.jsons.jackson;

import common.basic.facades.jsons.IJsonEngine;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonEngineJackson implements IJsonEngine {
    @Override
    public String toJsonString(Object o) {
        return JacksonUtil.toJsonCatches(o);
    }

    @Override
    public <T> T parse(String json, Class<T> clazz) {
        return JacksonUtil.fromJsonCatches(json, clazz);
    }

    @Override
    public <T> T parse(InputStream inputStream, Class<T> clazz) {
        return JacksonUtil.fromJsonCatches(inputStream, clazz);
    }

    @Override
    public Map<String, Object> parse(String json) {
        return JacksonUtil.toMapCatches(json);
    }

    @Override
    public <T> List<T> parseList(String json, Class<T> clazz) {
        return JacksonUtil.toListCatches(json, clazz);
    }

    @Override
    public <T> List<T> parseList(InputStream inputStream, Class<T> clazz) {
        return JacksonUtil.toListCatches(inputStream, clazz);
    }

    @Override
    public List<Map<String, Object>> parseList(String json) {
        return JacksonUtil.toListMapCatches(json);
    }
}
