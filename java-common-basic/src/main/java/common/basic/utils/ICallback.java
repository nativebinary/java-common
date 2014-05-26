package common.basic.utils;

public interface ICallback<T> {
    void onSuccess(T t);
    void onFail(Exception e);
}
