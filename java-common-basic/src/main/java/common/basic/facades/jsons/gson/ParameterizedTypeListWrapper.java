package common.basic.facades.jsons.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ParameterizedTypeListWrapper<T> implements ParameterizedType {

    private Class<T> classWrapped;

    public ParameterizedTypeListWrapper(Class<T> classWrapped) {
        this.classWrapped = classWrapped;
    }

    public Type[] getActualTypeArguments() {
        return new Type[]{classWrapped};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }
}

