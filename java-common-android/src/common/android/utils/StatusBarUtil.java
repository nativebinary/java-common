package common.android.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;

public class StatusBarUtil {

    public static boolean isVisible(Activity activity)
    {

        Window window = activity.getWindow();
        int windowVisibility = window.getDecorView().getWindowVisibility();

        return windowVisibility == View.VISIBLE;
    }

    public static int getHeight(Activity activity)
    {
        Window window = activity.getWindow();
        final View decorView = window.getDecorView();
        final Rect windowVisibleDisplayFrame = ViewUtil.getWindowVisibleDisplayFrame(decorView);
        return windowVisibleDisplayFrame.top;
    }
}
