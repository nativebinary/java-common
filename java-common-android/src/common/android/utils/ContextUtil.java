package common.android.utils;

import android.app.ActivityManager;
import android.content.Context;

public class ContextUtil {
    public static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }
}
