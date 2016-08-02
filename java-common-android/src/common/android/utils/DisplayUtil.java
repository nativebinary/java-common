package common.android.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import common.basic.geometiries.Size;

public class DisplayUtil {
    public static Size getSizeDisplay(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        return getPointSize(display);
    }

    public static Size getPointSize(Display display) {
        return new Size(display.getWidth(), display.getHeight());
    }
}