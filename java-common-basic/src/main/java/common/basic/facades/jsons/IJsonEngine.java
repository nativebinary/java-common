package common.basic.facades.jsons;

import common.basic.generics.TypeWrapperT;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IJsonEngine {

    String stringify(Object o);

    <T> T parse(String json, Class<T> clazz);
    <T> T parse(InputStream inputStream, Class<T> clazz);

    <T> T parse(String json, TypeWrapperT<T> typeWrapperT);
    <T> T parse(InputStream inputStream, TypeWrapperT<T> typeWrapperT);

    <T> List<T> parseList(String json, Class<T> clazz);
    <T> List<T> parseList(InputStream inputStream, Class<T> clazz);
    List<Map<String, Object>> parseList(String json);
}

