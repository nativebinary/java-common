package common.android.utils;

import android.os.Handler;

public class HandlerUtil {
    public static boolean postDelayed(int delayMillis, Runnable runnable) {
        return new Handler().postDelayed(runnable, delayMillis);
    }
}
