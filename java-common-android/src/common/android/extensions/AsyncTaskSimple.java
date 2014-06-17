package common.android.extensions;

import android.os.AsyncTask;
import common.basic.utils.Cast;

public abstract class AsyncTaskSimple<T> {

    public AsyncTaskSimple() {
        new AsyncTask<Void, Void, Object>() {
            @Override
            protected Object doInBackground(Void... params) {
                try {
                    return AsyncTaskSimple.this.doInBackground();
                }
                catch (Exception e) {
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                final Exception exception = Cast.as(o, Exception.class);
                if(null != exception) {
                    onFail(exception);
                    return;
                }

                onSuccess((T) o);
            }
        }.execute();
    }

    protected abstract T doInBackground() throws Exception;
    protected abstract void onSuccess(T t);
    protected abstract void onFail(Exception exception);
}
