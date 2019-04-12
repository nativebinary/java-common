package common.basic.interfaces;

import java.util.List;

public interface ICallbackReduce<T, F> {
    F callback(F s, T v, int i, List<T> a);
}
