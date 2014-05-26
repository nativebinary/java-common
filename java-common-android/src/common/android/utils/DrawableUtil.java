package common.android.utils;

import android.graphics.drawable.Drawable;
import common.basic.geometiries.Size;

public class DrawableUtil {

    public static Size getSizeIntrinsic(Drawable drawable) {
        return new Size(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
}
