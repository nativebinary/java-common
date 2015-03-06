package common.basic.facades.jsons;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JsonTypeT<T> implements Comparable<JsonTypeT<T>> {
    protected final Type _type;

    protected JsonTypeT() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class<?>) { // sanity check, should never happen
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        _type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return _type;
    }

    @Override
    public int compareTo(JsonTypeT<T> o) {
        // just need an implementation, not a good one... hence:
        return 0;
    }

    public T newInstance() {
        Class<T> instanceType = getClassT();
        try {
            return instanceType.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Class<T> getClassT() {
        Class<T> instanceType;
        if (_type instanceof ParameterizedType) {
            instanceType = (Class<T>) ((ParameterizedType) _type).getRawType();
        } else {
            instanceType = (Class<T>) _type;
        }
        return instanceType;
    }
}
