package common.android.utils;

import android.os.Build;
import android.widget.RelativeLayout;

public class RelativeLayoutLayoutParamsUtil {

    public static boolean contains(RelativeLayout.LayoutParams layoutParams, int verb) {
        for (int i : layoutParams.getRules()) {
            if(i == verb)
                return true;
        }

        return false;
    }

    public static void removeRule(RelativeLayout.LayoutParams layoutParams, int verb) {
        if (Build.VERSION.SDK_INT > 16) {
            layoutParams.removeRule(verb);
            return;
        }

        if(layoutParams.getRules().length > verb)
            layoutParams.addRule(verb, 0);
    }
}
