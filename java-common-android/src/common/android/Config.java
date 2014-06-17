package common.android;

import android.os.Build;
import common.basic.logs.Logger;
import common.basic.utils.StringUtil;

public class Config {
    static final String serverUcloud = "android.nativebinary.com:80";
    static final String aha00a = "192.168.56.1:9000";
    static final String silsol = "192.168.56.1:9000";
    static final String kig1945 = "192.168.56.1:9000";

    public static String getServerHostEndPoint(){
        final String model = Build.MODEL;

        if (StringUtil.equals(model, "20131025 Galaxy S4  HTC One  Xperia Z - 4.2.2 - with Google Apps - API 17 - 1080x1920"))
        {
//            return serverUcloud;
            return aha00a;
        }

        if (StringUtil.equals(model, "silsol"))
        {
//            return serverUcloud;
            return silsol;
        }

        if (StringUtil.equals(model, "kig1945") )
        {
//            return serverUcloud;
            return kig1945;
        }

        Logger.i(model);
        return serverUcloud;
    }
}
