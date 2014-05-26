package common.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

public class FragmentUtil {

    public static Fragment get(Activity activity, String tag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        return fragmentManager.findFragmentByTag(tag);
    }

    public static void add(Activity activity, String tag, Fragment fragment) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().add(fragment, tag).commit();
    }

    public static void remove(Activity activity, String tag) {
        final Fragment fragment = get(activity, tag);
        if(null == fragment)
            return;

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().remove(fragment).commit();
    }
}
