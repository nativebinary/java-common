package common.android.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import common.basic.geometiries.Margin;

public class LinearLayoutLayoutParamsUtil {
    public static void setMarginRight(View view, int i) {
        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) view.getLayoutParams();
        param.setMargins(param.leftMargin, param.topMargin, i, param.bottomMargin);
        view.setLayoutParams(param);
    }

    public static void setMatchParentWeight(View viewChild, int weight) {
        final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewChild.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.weight = 1;
    }

    public static void setMargin(View view, Margin margin) {
        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) view.getLayoutParams();
        param.setMargins(margin.left, margin.bottom, margin.right, margin.bottom);
        view.setLayoutParams(param);
    }
}
