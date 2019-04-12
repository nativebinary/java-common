package common.basic.interfaces;

import java.util.List;

public interface ICallbackBoolean<T> {
    boolean callback(T v, int i, List<T> a);
}
