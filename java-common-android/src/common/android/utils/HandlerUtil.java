package common.android.utils;

import android.os.Handler;

public class HandlerUtil {
    public static boolean postDelayed(int delayMillis, Runnable runnable) {
        return postDelayed(new Handler(), delayMillis, runnable);
    }

    public static boolean postDelayed(Handler handler, int delayMillis, Runnable runnable) {
        return handler.postDelayed(runnable, delayMillis);
    }
}
