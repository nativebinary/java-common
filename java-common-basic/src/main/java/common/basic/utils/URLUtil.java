package common.basic.utils;

import common.basic.logs.Logger;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLUtil {

    public URLUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String escape(String url) {
        return escapeSpace(escapePipe(url));
    }

    public static String escapePipe(String url) {
        return url.replaceAll("\\|", "%7c");
    }

    public static String escapeSpace(String url) {
        return url.replaceAll(" ", "%20");
    }

    public static String encode(String s) {
        try {
            return URLEncoder.encode(s, "utf-8");
        }
        catch (NullPointerException e) {
            Logger.e(e);
            return "";
        }
        catch (UnsupportedEncodingException ignored) {
            return "";
        }
    }

    public static String decodeURIComponentIfEncoded(String url) {
        if (url.matches("(?i)^https?%3a%2f%2f.+")) {
            String urlDecoded = decode(url);
            Logger.i("%s -> %s", url, urlDecoded);
            return urlDecoded;
        }

        return url;
    }

    public static String decode(String s) {
        try {
            return URLDecoder.decode(s, "utf-8");
        }
        catch (NullPointerException e) {
            Logger.e(e);
            return "";
        }
        catch (UnsupportedEncodingException ignored) {
            return "";
        }
    }

    public static Map<String, List<String>> getMapListParameter(String url) throws UnsupportedEncodingException {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        String[] urlParts = url.split("\\?");
        if (urlParts.length > 1) {
            String query = urlParts[1];
            for (String param : query.split("&")) {
                String pair[] = param.split("=");
                String key = URLDecoder.decode(pair[0], "UTF-8");
                String value = "";
                if (pair.length > 1) {
                    value = URLDecoder.decode(pair[1], "UTF-8");
                }
                List<String> values = params.get(key);
                if (values == null) {
                    values = new ArrayList<String>();
                    params.put(key, values);
                }
                values.add(value);
            }
        }
        return params;

    }

    public static String prependHttpIfNoProtocol(String url) {
        if (StringUtil.isNullOrWhitespace(url)) {
            return url;
        }

        if (isHttpOrHttps(url)) {
            return url;
        }

        return "http://" + url;
    }

    public static boolean isHttpOrHttps(String url) {
        return isHttp(url) || isHttps(url);
    }

    public static boolean isHttps(String url) {
        return url.startsWith("https://");
    }

    public static boolean isHttp(String url) {
        return url.startsWith("http://");
    }

    public static String makeQueryString(Map<String, Object> map) {
        List<String> list = new ArrayList<String>(map.size());
        for (String key : map.keySet()) {
            list.add(String.format("%s=%s", encode(key), encode(map.get(key).toString())));
        }

        return StringUtil.join("&", list);
    }

    public static URL create(String url) {
        try {
            return new URL(url);
        }
        catch (MalformedURLException e) {
            Logger.e(e);
            return null;
        }
    }



    public static String toFilename(String sUri)
    {
        return sUri
                .replace('\\', '＼')
                .replace('/', '／')
                .replace(':', '：')
                .replace('*', '∗')
                .replace('?', '？')
                .replace('"', '〃')
                .replace('<', '〈')
                .replace('>', '〉')
                .replace('|', '｜');
    }

    public static String toUriString(String sFileString)
    {
        return sFileString
                .replace('＼', '\\')
                .replace('／', '/')
                .replace('：', ':')
                .replace('∗', '*')
                .replace('？', '?')
                .replace('〃', '"')
                .replace('〈', '<')
                .replace('〉', '>')
                .replace('｜', '|');
    }
}
