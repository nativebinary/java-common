package common.android.utils;

import android.widget.ScrollView;

public class ScrollViewUtil {
    public static int getContentHeight(ScrollView scrollView) {
        return  scrollView.getChildAt(0).getHeight();
    }
}
