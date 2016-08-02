package common.android.utils;

import android.app.ActionBar;
import android.app.Activity;

public class ActionBarUtil {
    public static int getHeight(Activity activity) {
        final ActionBar actionBar = activity.getActionBar();
        if(null == actionBar)
            return 0;

        return actionBar.getHeight();
    }
}
