package common.android.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

import java.util.ArrayList;
import java.util.List;

public class ViewGroupUtil {
    public static void setBackgroundColorChildForDebug(ViewGroup viewGroup) {
        final int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childAt = viewGroup.getChildAt(i);
            if(childAt == null)
                continue;

            childAt.setBackgroundColor(ColorUtil.fromHSV(i * 360 / childCount, 0.5f, 0.5f));
        }
    }

    public static List<View> getListViewChild(ViewGroup viewGroup) {
        List<View> listView = new ArrayList<View>();

        final int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View viewChild = viewGroup.getChildAt(i);
            if(viewChild instanceof Space)
                listView.add(viewChild);
        }

        return listView;
    }
}
