package common.basic.facades.jsons.gson;

import com.google.gson.reflect.TypeToken;
import common.basic.facades.jsons.IJsonEngine;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonEngineGson implements IJsonEngine {
    @Override
    public String toJson(Object o) {
        return GsonUtil.toJson(o);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return GsonUtil.fromJson(json, clazz);
    }

    @Override
    public List<Object> toList(String json) {
        return GsonUtil.toList(json, new TypeToken<List<Object>>() {});
    }

    @Override
    public <T> List<T> toList(String json, Class<T> clazz) {
        return GsonUtil.toList(json, new TypeToken<List<T>>() {});
    }

    @Override
    public Map<String, Object> toMap(String json) {
        return GsonUtil.fromJsonMap(json);
    }

    @Override
    public List<Map<String, Object>> toListMap(String json) {
        return GsonUtil.toList(json, new TypeToken<List<Map<String, Object>>>() {});
    }

    @Override
    public <T> T toT(String json, Class<T> clazz) {
        return GsonUtil.fromJson(json, clazz);
    }

    @Override
    public <T> List<T> toListT(String json, Class<T> clazz) {
        return GsonUtil.toList(json, new TypeToken<List<T>>() {});
    }

    @Override
    public <T> T toT(InputStream json, Class<T> clazz) {
        return GsonUtil.fromJson(json, clazz);
    }

    @Override
    public <T> List<T> toListT(InputStream json, Class<T> clazz) {
        return GsonUtil.toList(json, new TypeToken<List<T>>() {});
    }
}
