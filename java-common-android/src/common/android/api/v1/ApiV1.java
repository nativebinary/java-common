package common.android.api.v1;

import android.content.Context;
import common.android.Config;
import common.android.utils.AndroidHttpClientUtil;
import common.android.utils.AndroidHttpClientUtilException;
import common.android.utils.HttpEntityUtil;
import common.basic.utils.HashMapStringObject;
import common.basic.utils.URLUtil;

import java.io.File;
import java.io.IOException;

public class ApiV1 {
    static String getScheme() {
        return "http://";
    }

    static String getEndPoint() {
        return Config.getServerHostEndPoint();
    }

    static String getServer() {
        return getScheme() + getEndPoint();
    }

    static String getUrlAndQueryString(String path, HashMapStringObject mapParameter) {
        if(mapParameter.size() == 0)
            return getServerPath(path);

        return getServerPath(path) + "?" + URLUtil.makeQueryString(mapParameter);
    }

    private static String getServerPath(String path) {return getServer() + "/api/v1/" + path;}

    public static String httpGetReturnsString(Context context, String path, HashMapStringObject mapParameter) throws AndroidHttpClientUtilException {
        try {
            return AndroidHttpClientUtil.httpGetReturnsString(context, getUrlAndQueryString(path, mapParameter));
        }
        catch (IOException e) {
            throw new AndroidHttpClientUtilException(e);
        }
    }

    public static String httpPostReturnsString(Context context, String path, HashMapStringObject mapParameter) throws AndroidHttpClientUtilException {
        try {
            return AndroidHttpClientUtil.httpPostReturnsString(context, getServerPath(path), mapParameter);
        }
        catch (IOException e) {
            throw new AndroidHttpClientUtilException(e);
        }
    }

    public static void httpGetSaveToFile(Context context, String path, HashMapStringObject mapParameter, File file, final HttpEntityUtil.ICallbackToFile callbackToFile) throws AndroidHttpClientUtilException {
        try {
            AndroidHttpClientUtil.httpGetSaveToFile(context, getUrlAndQueryString(path, mapParameter), file, callbackToFile);
        }
        catch (IOException e) {
            throw new AndroidHttpClientUtilException(e);
        }
    }
}
