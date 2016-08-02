package common.android.utils;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import common.basic.geometiries.Size;

public class ResourceUtil {
    public static float dp2px(Resources resources, float dp){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return px;
    }
    public static int dp2pxInt(Resources resources, float dp){
        return (int)dp2px(resources, dp);
    }

    public static Size getSizeDrawable(Resources resources, int resourceId) {
        final Drawable drawable = resources.getDrawable(resourceId);
        return new Size(drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }

    public static boolean isLandscape(Resources resources) {
        return resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isPortrait(Resources resources) {
        return resources.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }
}
