package common.basic.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeWrapperT<T> implements Comparable<TypeWrapperT<T>> {
    protected final Type _type;

    protected TypeWrapperT() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class<?>) {
            throw new IllegalArgumentException("TypeWrapperT");
        }
        _type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return _type;
    }

    @Override
    public int compareTo(TypeWrapperT<T> o) {
        return null != this._type && this._type.equals(o._type) ? 0 : 1;
    }

}
