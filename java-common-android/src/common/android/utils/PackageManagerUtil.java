package common.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import common.basic.logs.Logger;

public class PackageManagerUtil {

    public final static String PackageNameHelpHub = "com.samsung.helphub";
    public final static String PackageNameMobilePrint = "com.sec.android.app.mobileprint";
    public final static String PackageNameSamsungApps = "com.sec.android.app.samsungapps";

    public static boolean isPackageInstalled(Context context, String packageName) {
        return null != getPackageInfo(context, packageName);
    }

    public static PackageInfo getPackageInfo(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA);
            if (null == packageInfo)
            {
                Logger.e();
                return null;
            }
            return packageInfo;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
            return null;
        }

    }
}
