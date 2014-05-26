package common.android.utils;

import android.widget.LinearLayout;

public class LinearLayoutUtil {

    public static int getOrientation(boolean horizontal) {
        if(horizontal)
            return LinearLayout.HORIZONTAL;

        return LinearLayout.VERTICAL;
    }
}
