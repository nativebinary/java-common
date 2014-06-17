package common.play1.utils;

import common.basic.utils.StringUtil;
import play.mvc.Http;
import play.mvc.Http.Request;

public class RequestUtil {

	public static String getHeaderValue(Request request, String headerName) {
		play.mvc.Http.Header header = request.headers.get(headerName);
		if(null == header)
			return "";
		
		String value = header.value();
		if(null == value)
			return "";
		
		return value;
	}

	public static boolean isMobileAgent(String userAgent) {
		if(userAgent.contains("Android"))
			return true;
		
		if(userAgent.contains("iPhone"))
			return true;

		if(userAgent.contains("iPod"))
		  return true;
		
		//if(userAgent.contains("iPad"))
		//	return true;

		if(userAgent.contains("BlackBerry"))
			return true;

		if(userAgent.contains("webOS"))
			return true;
		
		return false;

	}

    public static String getUserAgent(Request request) {
        return getHeaderValue(request, "user-agent");
    }

    public static boolean isMobileAgent(Request request) {
        return isMobileAgent(getUserAgent(request));
    }

    public static boolean isIE_6_7_8(Request request) {
        final String userAgent = getUserAgent(request);
        if(userAgent.contains("MSIE 6") || userAgent.contains("MSIE 7") || userAgent.contains("MSIE 8"))
            return true;

        return false;
    }

    public static boolean isMatchController(Request request, String controllerName) {
        String[] array = request.url.toLowerCase().split("/");
        if(array.length <= 1)
            return false;

        if(StringUtil.equals(controllerName.toLowerCase(), array[1]))
            return true;

        return false;
    }


    public static boolean getCookie(Request request, String key, boolean bDefault) {
        Http.Cookie cookie = request.cookies.get(key);

        if (null == cookie)
            return bDefault;

        return common.basic.utils.BooleanUtil.parse(cookie.value);
    }

    public static void setCookie(Http.Response response, String key, boolean value) {
        response.setCookie(key, common.basic.utils.BooleanUtil.toString(value));
    }


}
