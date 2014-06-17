package common.android.extensions;

import common.basic.interfaces.ICallback;

public abstract class AsyncTaskCallback<T> extends AsyncTaskSimple<T> {

    private final ICallback<T> callback;

    public AsyncTaskCallback(final ICallback<T> callback) {
        this.callback = callback;
    }

    protected abstract T doInBackground() throws Exception;

    @Override
    protected void onSuccess(T t) {
        callback.onSuccess(t);
    }

    @Override
    protected void onFail(Exception exception) {
        callback.onFail(exception);
    }
}
