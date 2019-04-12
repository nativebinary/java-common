package common.basic.interfaces;

import java.util.List;

public interface ICallbackMap<T, F> {
    F callback(T v, int i, List<T> a);
}
