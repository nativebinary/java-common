package common.basic.helper;

import common.basic.facades.jsons.JsonUtil;
import common.basic.facades.jsons.jackson.JacksonUtil;
import common.basic.logs.Logger;
import common.basic.utils.ParamBuilder;
import common.basic.utils.ThreadUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestHelper {

    public static class HttpClientConnManager {

        static HttpParams params;
        static ClientConnectionManager cm;

        static int connectionTimeOut = 7 * 1000;

        static
        {
            params = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(params, connectionTimeOut);
            HttpConnectionParams.setSoTimeout(params, connectionTimeOut);
            ConnManagerParams.setMaxTotalConnections(params, 100);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

            cm = new ThreadSafeClientConnManager(params, schemeRegistry);
        }

        public static void setConnectionTimeOut(int timeOut) {
            HttpConnectionParams.setConnectionTimeout(params, timeOut);
            HttpConnectionParams.setSoTimeout(params, timeOut);
        }

        public static HttpClient createHttpClient() {
            return new DefaultHttpClient(cm, params);
        }

        public HttpClientConnManager() throws InstantiationException {
            throw new InstantiationException();
        }
    }

    public interface IResponse {
        void onResponse(HttpResponse httpResponse);

        void onException(Exception e);
    }

    public interface IResponseText {
        void onResponse(String responseText);

        void onException(Exception e);
    }

    public interface IResponseByteArray {
        void onResponse(byte[] responseByteArray);

        void onException(Exception e);
    }

    private enum HttpMethod {
        Get,
        Post
    }

    private static HttpRequestBase getHttpRequestBase(HttpMethod httpMethod, URI uri, List<NameValuePair> listParam) throws URISyntaxException, UnsupportedEncodingException {
        final List<NameValuePair> list = new ArrayList<NameValuePair>();

        for (NameValuePair nameValuePair : listParam)
        {
            if (null == nameValuePair.getValue())
                continue;

            list.add(nameValuePair);
        }


        if (httpMethod == HttpMethod.Post) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            return httpPost;
        }

        return new HttpGet(new URI(uri + "?" + URLEncodedUtils.format(list, "UTF-8")));
    }

    public static HttpResponse getHttpResponse(final HttpMethod httpMethod, final String urlString, final List<NameValuePair> listParam) throws IOException, URISyntaxException {
        URI uri = new URI(urlString);

        //HttpClient httpClient = new DefaultHttpClient();
        HttpClient httpClient = HttpClientConnManager.createHttpClient();

        HttpRequestBase httpRequestBase = getHttpRequestBase(httpMethod, uri, listParam);
        return httpClient.execute(httpRequestBase);
    }

    public static HttpResponse get(final String url, final List<NameValuePair> listParam) throws IOException, URISyntaxException {
        return getHttpResponse(HttpMethod.Get, url, listParam);
    }

    public static HttpResponse post(final String url, final List<NameValuePair> listParam) throws IOException, URISyntaxException {
        return getHttpResponse(HttpMethod.Post, url, listParam);
    }

    public static void request(final HttpMethod httpMethod, final String urlString, final List<NameValuePair> listParam, final IResponse response) {

        ThreadUtil.createBackgroundThread("", new Runnable() {
            @Override
            public void run() {
                try {
                    HttpResponse httpResponse = getHttpResponse(httpMethod, urlString, listParam);
                    response.onResponse(httpResponse);
                } catch (Exception e) {
                    Logger.e("HttpRequestHelper : request [URL=" + urlString + "; PRAM=" + JsonUtil.stringify(listParam) + "; ERR=" + e.toString() + ";]");
                    response.onException(e);
                }

            }
        }).run();
    }

    public static void get(final String url, final List<NameValuePair> listParam, final IResponse response) {
        request(HttpMethod.Get, url, listParam, response);
    }

    public static void get(final String url, final List<NameValuePair> listParam, final IResponseText response) {

        get(url, listParam, new IResponse() {
            @Override
            public void onResponse(HttpResponse httpResponse) {
                try {

                    response.onResponse(EntityUtils.toString(httpResponse.getEntity()));
                } catch (Exception e) {
                    response.onException(e);
                }
            }

            @Override
            public void onException(Exception e) {
                response.onException(e);
            }
        });
    }

    public static void post(final String url, final List<NameValuePair> listParam, final IResponse response) {
        request(HttpMethod.Post, url, listParam, response);
    }

    public static void post(final String url, final List<NameValuePair> listParam, final IResponseText response) {

        post(url, listParam, new IResponse() {
            @Override
            public void onResponse(HttpResponse httpResponse) {
                try {
                    response.onResponse(EntityUtils.toString(httpResponse.getEntity()));
                } catch (Exception e) {
                    response.onException(e);
                }
            }

            @Override
            public void onException(Exception e) {
                response.onException(e);
            }
        });
    }

    public static void post(final String url, final List<NameValuePair> listParam, final IResponseByteArray response) {
        request(HttpMethod.Post, url, listParam, new IResponse() {
            @Override
            public void onResponse(HttpResponse httpResponse) {
                try {
                    final byte[] byteArray = EntityUtils.toByteArray(httpResponse.getEntity());
                    response.onResponse(byteArray);
                } catch (IOException e) {
                    Logger.e(e);
                    response.onException(e);
                }

            }

            @Override
            public void onException(Exception e) {
                response.onException(e);
            }
        });
    }

    public static String responseText(HttpResponse httpResponse) throws IOException {
        return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
    }

    @Deprecated
    public static Map<String, Object> parseMap(HttpResponse httpResponse) throws IOException {
        return JacksonUtil.toMap(responseText(httpResponse));
    }

    @Deprecated
    public static List<Map<String, Object>> parseListMap(HttpResponse httpResponse) throws IOException {
        return JsonUtil.parseList(responseText(httpResponse));
    }

    public static String getTextSync(String url) {
        try {
            ParamBuilder listParam = new ParamBuilder();

            if (url.contains("?")) {
                int i = url.indexOf("?");
                String param = url.substring(i + 1);
                url = url.substring(0, i);
                String[] split = param.split("&");
                for (String keyValue : split) {
                    String[] pair = keyValue.split("=");
                    if (pair.length == 2) {
                        listParam.append(pair[0], pair[1]);
                    }
                }
            }

            HttpResponse httpResponse = getHttpResponse(HttpMethod.Get, url, listParam);
            return responseText(httpResponse);
        } catch (Exception e) {
            return "";
        }
    }
}