package common.basic.interfaces;

public interface ICallback<T> {
    void onSuccess(T t);
    void onFail(Exception e);
}
