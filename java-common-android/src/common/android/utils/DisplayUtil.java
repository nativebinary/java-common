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
        Point point = new Point();
        display.getSize(point);
        return new Size(point.x,point.y);
    }
}