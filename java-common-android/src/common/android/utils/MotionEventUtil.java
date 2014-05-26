package common.android.utils;

import android.view.MotionEvent;

public class MotionEventUtil {
    public static boolean isMultiTouch(MotionEvent motionEvent) {
        return 1 < motionEvent.getPointerCount();
    }
}
