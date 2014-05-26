package common.android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MetaData {
    private static final String prefix = "android.MetaData.";

    public static boolean isDev(Context context) {
        return get(context, "isDev", false);
    }

    private static boolean get(Context context, String key, boolean defaultValue) {
        try {
            return getMetaData(context).getBoolean(prefix + key, defaultValue);
        }
        catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }

    private static long get(Context context, String key, long defaultValue) {
        try {
            return getMetaData(context).getLong(prefix + key, defaultValue);
        }
        catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }

    private static String get(Context context, String key, String defaultValue) {
        try {
            return getMetaData(context).getString(prefix + key, defaultValue);
        }
        catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }

    private static Bundle getMetaData(Context context) throws PackageManager.NameNotFoundException {
        return getApplicationInfo(context).metaData;
    }

    private static ApplicationInfo getApplicationInfo(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
    }


}
