package common.android.utils;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import common.basic.geometiries.Size;

public class RectUtil {
    public static Rect toRect(RectF rectF) {
        return new Rect(
                (int)rectF.left,
                (int)rectF.top,
                (int)rectF.right,
                (int)rectF.bottom);
    }

    public static PointF getPointCenter(RectF rectF) {
        return new PointF(rectF.centerX(), rectF.centerY());
    }

    public static Rect create(Point pointViewTarget, Size sizeViewTarget) {
        return new Rect(
                pointViewTarget.x,
                pointViewTarget.y,
                pointViewTarget.x + sizeViewTarget.width - 1,
                pointViewTarget.y + sizeViewTarget.height - 1);


    }
}
