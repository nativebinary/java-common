package common.basic.jsons;

class IJsonJackson implements IJson {
    @Override
    public String toJson(Object o) {
        return JacksonUtil.toJsonCatches(o);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return JacksonUtil.fromJsonCatches(json, clazz);
    }
}
