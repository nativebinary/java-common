package common.play1.logics;

import common.basic.utils.IntUtil;
import common.basic.utils.StringUtil;
import common.play1.utils.ApplicationConfUtil;

public class ApplicationConfBase {
    public static String application_name(){
        return ApplicationConfUtil.getProperty("application.name");
    }

    public static String application_mode(){
        return ApplicationConfUtil.getProperty("application.mode");
    }

    public static boolean isDev() {
        return StringUtil.equals(application_mode(), "dev");
    }

    protected static String getProperty(String key) {
        return ApplicationConfUtil.getProperty(key);
    }

    protected static int getProperty(String key, int defaultValue) {
        return IntUtil.parseInt(getProperty(key), defaultValue);
    }
}
