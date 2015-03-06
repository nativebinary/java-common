package common.basic.facades.jsons.gson;

import com.google.gson.reflect.TypeToken;
import common.basic.facades.jsons.IJsonEngine;
import common.basic.facades.jsons.JsonTypeT;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonEngineGson implements IJsonEngine {
    @Override
    public String stringify(Object o) {
        return GsonUtil.stringify(o);
    }

    @Override
    public <T> T parse(String json, Class<T> clazz) {
        return GsonUtil.parse(json, clazz);
    }

    @Override
    public <T> T parse(String json, JsonTypeT<T> jsonTypeT) {
        return GsonUtil.parse(json, jsonTypeT);
    }

    @Override
    public <T> T parse(InputStream inputStream, Class<T> clazz) {
        return GsonUtil.parse(inputStream, clazz);
    }


    @Override
    public <T> T parse(InputStream inputStream, JsonTypeT<T> jsonTypeT) {
        return GsonUtil.parse(inputStream, jsonTypeT);
    }

    @Override
    public Map<String, Object> parse(String json) {
        return GsonUtil.parse(json);
    }

    @Override
    public <T> List<T> parseList(String json, Class<T> clazz) {
        return GsonUtil.parseList(json, clazz);
    }

    @Override
    public <T> List<T> parseList(InputStream inputStream, Class<T> clazz) {
        return GsonUtil.parseList(inputStream, clazz);
    }

    @Override
    public List<Map<String, Object>> parseList(String json) {
        return GsonUtil.parseList(json, new TypeToken<List<Map<String, Object>>>() {});
    }
}
