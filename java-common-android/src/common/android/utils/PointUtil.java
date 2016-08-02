package common.android.utils;

import android.graphics.Point;
import android.graphics.PointF;

public class PointUtil {

    public static Point create(int[] arrayInt) {
        if(arrayInt == null)
            return null;

        if(arrayInt.length != 2)
            return null;

        return new Point(arrayInt[0], arrayInt[1]);
    }

    public static Point toPoint(PointF pointF) {
        return new Point((int) pointF.x, (int) pointF.y);
    }
}
