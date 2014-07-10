package common.basic.jsons;

import com.google.gson.reflect.TypeToken;

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
        return GsonUtil.fromJsonArray(json, new TypeToken<List<Object>>() {});
    }

    @Override
    public Map<String, Object> toMap(String json) {
        return GsonUtil.fromJsonMap(json);
    }

    @Override
    public List<Map<String, Object>> toListMap(String json) {
        return GsonUtil.fromJsonArray(json, new TypeToken<List<Map<String, Object>>>() {});
    }
}
