package common.basic.facades.jsons.gson;

import common.basic.facades.jsons.IJsonEngine;
import common.basic.generics.TypeWrapperT;

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
    public <T> T parse(InputStream inputStream, Class<T> clazz) {
        return GsonUtil.parse(inputStream, clazz);
    }

    @Override
    public <T> T parse(String json, TypeWrapperT<T> typeWrapperT) {
        return GsonUtil.parse(json, typeWrapperT);

    }

    @Override
    public <T> T parse(InputStream inputStream, TypeWrapperT<T> typeWrapperT) {
        return GsonUtil.parse(inputStream, typeWrapperT);

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
        return GsonUtil.parse(json, new TypeWrapperT<List<Map<String, Object>>>(){});
    }
}
