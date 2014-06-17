package logics;

import common.play1.logics.ApplicationConfBase;

public class ApplicationConf extends ApplicationConfBase {

    public static String initial_admin_email() {
        return getProperty("initial.admin.email");
    }

    public static String initial_admin_password() {
        return getProperty("initial.admin.password");
    }

    public static String fileStorage_path() {
        return getProperty("fileStorage.path");
    }

    public static int api_result_limit() {
        return getProperty("api.result.limit", 10);
    }

    public static String getGcmApiKey() {
        return getProperty("gcm.api.key");
    }
}
