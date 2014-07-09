package common.basic.jsons;

public interface IJson {
    String toJson(Object o);
    <T> T fromJson(String json, Class<T> clazz);
}
