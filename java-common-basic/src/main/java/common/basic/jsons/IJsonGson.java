package common.basic.jsons;

class IJsonGson implements IJson {
    @Override
    public String toJson(Object o) {
        return GsonUtil.toJson(o);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return GsonUtil.fromJson(json, clazz);
    }
}
