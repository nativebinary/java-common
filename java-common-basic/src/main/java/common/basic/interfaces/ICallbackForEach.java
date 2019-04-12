package common.basic.interfaces;

import java.util.List;

public interface ICallbackForEach<T> {
    void callback(T v, int i, List<T> a);
}
