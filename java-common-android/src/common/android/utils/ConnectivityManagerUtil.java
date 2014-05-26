package common.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import common.basic.logs.Logger;

public class ConnectivityManagerUtil {

    public static boolean isConnectedOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        int[] arrayNetworkType = new int[] {ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIMAX};

        for (int networkType : arrayNetworkType)
        {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(networkType);
            if (null == networkInfo)
                continue;

            if (networkInfo.isConnectedOrConnecting())
                return true;
        }

        NetworkInfo dataNetwork = connectivityManager.getActiveNetworkInfo();
        if(null == dataNetwork)
        {
            Logger.e("null == dataNetwork");
            return false;
        }

        return dataNetwork.isConnectedOrConnecting();
    }
}
