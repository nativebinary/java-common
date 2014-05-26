package common.android.utils;

import android.os.Handler;
import android.os.Looper;
import common.basic.logs.Logger;
import common.basic.utils.ICallback;
import common.basic.utils.ICallbackVoid;

public class ThreadUtil {

    public static abstract class CallbackRunOnNotUiThreadAndRunCallbackOnUiThread<T> implements ICallback<T> {
        private final ICallback<T> callback;

        protected CallbackRunOnNotUiThreadAndRunCallbackOnUiThread(ICallback<T> callback) {
            this.callback = callback;
        }

        public abstract T onNotUiThread() throws Exception;

        @Override
        public void onSuccess(T t) {
            callback.onSuccess(t);
        }

        @Override
        public void onFail(Exception e) {
            callback.onFail(e);
        }
    }

    public abstract static class CallbackVoidRunOnNotUiThreadAndRunCallbackOnUiThread implements ICallbackVoid {
        private final ICallbackVoid callbackVoid;

        protected CallbackVoidRunOnNotUiThreadAndRunCallbackOnUiThread(ICallbackVoid callbackVoid) {
            this.callbackVoid = callbackVoid;
        }

        public abstract void onNotUiThread() throws Exception;

        @Override
        public void onSuccess() {
            callbackVoid.onSuccess();
        }

        @Override
        public void onFail(Exception e) {
            callbackVoid.onFail(e);
        }
    }


    public static boolean isUiThread() {
        return Looper.getMainLooper().equals(Looper.myLooper());
    }

    public static void runOnUiThread(Runnable runnable) {
        if(isUiThread())
        {
            runnable.run();
            return;
        }

        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static void runOnNotUiThread(String name, Runnable runnable) {
        if(isUiThread())
        {
            common.basic.utils.ThreadUtil.create(name, runnable).start();
            return;
        }

        runnable.run();
    }

    public static void sleep(int millis) {
        common.basic.utils.ThreadUtil.sleep(millis);
    }

    public static <T> void runOnNotUiThreadAndRunCallbackOnUiThread(final CallbackRunOnNotUiThreadAndRunCallbackOnUiThread<T> callbackRunOnNotUiThreadAndRunCallbackOnUiThread) {
        runOnNotUiThread("runOnNotUiThreadAndRunCallbackOnUiThread", new Runnable() {
            @Override
            public void run() {
                try {
                    final T list = callbackRunOnNotUiThreadAndRunCallbackOnUiThread.onNotUiThread();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callbackRunOnNotUiThreadAndRunCallbackOnUiThread.onSuccess(list);
                        }
                    });
                }
                catch (final Exception e) {
                    Logger.e(e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callbackRunOnNotUiThreadAndRunCallbackOnUiThread.onFail(e);
                        }
                    });
                }

            }
        });
    }

    public static <T> void runOnNotUiThreadAndRunCallbackOnUiThread(final CallbackVoidRunOnNotUiThreadAndRunCallbackOnUiThread callbackRunOnNotUiThreadAndRunCallbackOnUiThread) {
        runOnNotUiThread("runOnNotUiThreadAndRunCallbackOnUiThread", new Runnable() {
            @Override
            public void run() {
                try {
                    callbackRunOnNotUiThreadAndRunCallbackOnUiThread.onNotUiThread();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callbackRunOnNotUiThreadAndRunCallbackOnUiThread.onSuccess();
                        }
                    });
                }
                catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callbackRunOnNotUiThreadAndRunCallbackOnUiThread.onFail(e);
                        }
                    });
                }

            }
        });
    }

}
