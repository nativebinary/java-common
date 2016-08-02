package common.android.utils;

import android.content.Context;
import android.widget.Toast;
import common.basic.logs.Logger;

public class ToastUtil {
    public static void showShort(Context context, String s) {
        final int length = Toast.LENGTH_SHORT;
        show(context, s, length);
    }

    public static void showShort(Context context, int resourceId) {
        final int length = Toast.LENGTH_SHORT;
        show(context, context.getString(resourceId), length);
    }

    public static void showLong(Context context, String s) {
        final int length = Toast.LENGTH_SHORT;
        show(context, s, length);
    }

    public static void showLong(Context context, int resourceId) {
        final int length = Toast.LENGTH_SHORT;
        show(context, context.getString(resourceId), length);
    }


    private static void show(final Context context, final String s, final int length) {
        Logger.i(s);
        ThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, s, length).show();
            }
        });
    }
}
