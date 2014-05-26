package common.android.utils;

import android.content.Context;
import common.android.extensions.CookieStorePersistent;
import common.android.extensions.MultipartEntityProgress;
import common.basic.interfaces.ICallbackProgress;
import common.basic.logs.Logger;
import common.basic.utils.Cast;
import common.basic.utils.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class AndroidHttpClientUtil {

    static HttpContext localContext;
    static CookieStorePersistent basicCookieStore;

    public static boolean isPlaySessionLoggedIn(Context context) {
        init(context);

        for (Cookie cookie : basicCookieStore.getCookies()) {
            if(!StringUtil.equals(cookie.getName(), "PLAY_SESSION"))
                continue;

            final String value = cookie.getValue();
            return !StringUtil.isNullOrEmpty(value);
        }

        return false;
    }

    public static void clearCookieStore(Context context) {
        init(context);

        basicCookieStore.clear();
    }

    private static void init(Context context) {
        if(localContext == null)
        {
            localContext = new BasicHttpContext();
            basicCookieStore = new CookieStorePersistent(context);
            localContext.setAttribute(ClientContext.COOKIE_STORE, basicCookieStore);
        }
    }

    private static DefaultHttpClient createHttpClient(Context context) {
        init(context);
        return new DefaultHttpClient();
    }

    public static HttpEntity httpGet(Context context, String url) throws IOException, AndroidHttpClientUtilException {
        Logger.i(url);

        final HttpClient httpClient = createHttpClient(context);
        final HttpGet httpGet = new HttpGet(url);
        final HttpResponse httpResponse = httpClient.execute(httpGet, localContext);

        final StatusLine statusLine = httpResponse.getStatusLine();
        final int statusCode = statusLine.getStatusCode();
        final HttpEntity httpEntity = httpResponse.getEntity();

        if(statusCode != 200)
        {
            Logger.e("statusCode != 200", statusCode, url);
            throw new AndroidHttpClientUtilException(statusLine, httpEntity);
        }

        return httpEntity;
    }

    public static HttpEntity httpPost(Context context, String url, Map<String, Object> map) throws IOException, AndroidHttpClientUtilException {
        return httpPost(context, url, map, new ICallbackProgress() {
            @Override
            public void callback(long progress, long max) {
                Logger.d(progress, max);
            }
        });
    }

    public static HttpEntity httpPost(Context context, String url, Map<String, Object> map, ICallbackProgress callbackProgress) throws IOException, AndroidHttpClientUtilException {
        Logger.i(url);

        final HttpClient httpClient = createHttpClient(context);
        HttpPost httpPost = new HttpPost(url);

        final MultipartEntityProgress multipartEntity = new MultipartEntityProgress(HttpMultipartMode.STRICT, callbackProgress);

        for (String key : map.keySet()) {
            Object value = map.get(key);

            File file = Cast.as(value, File.class);
            if(file != null)
            {
                multipartEntity.addPart(key, new FileBody(file));
                continue;
            }

            if(value.getClass().isArray())
                Logger.e("value.getClass().isArray() : you can pass primitive type only.");

            multipartEntity.addPart(key, new StringBody(value.toString(), Charset.forName("UTF-8")));
        }

        httpPost.setEntity(multipartEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost, localContext);

        final StatusLine statusLine = httpResponse.getStatusLine();
        final int statusCode = statusLine.getStatusCode();
        final HttpEntity httpEntity = httpResponse.getEntity();

        if(statusCode != 200)
        {
            Logger.e("statusCode != 200", statusCode);
            throw new AndroidHttpClientUtilException(statusLine, httpEntity);
        }

        return httpEntity;
    }


    public static String httpGetReturnsString(Context context, String url) throws AndroidHttpClientUtilException, IOException {
        final HttpEntity httpEntity = httpGet(context, url);
        return HttpEntityUtil.toString(httpEntity);
    }

    public static String httpPostReturnsString(Context context, String url, Map<String, Object> map, ICallbackProgress callbackProgress) throws AndroidHttpClientUtilException, IOException {
        final HttpEntity httpEntity = httpPost(context, url, map, callbackProgress);
        return HttpEntityUtil.toString(httpEntity);
    }


    public static void httpGetSaveToFile(Context context, String url, File file, final ICallbackProgress callbackProgress) throws AndroidHttpClientUtilException, IOException {
        final HttpEntity httpEntity = httpGet(context, url);
        HttpEntityUtil.toFile(httpEntity, file, callbackProgress);
    }

}
