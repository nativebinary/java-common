package common.basic.utils;

public class FacebookUtil {
    public static String getUrlProfileImageLarge(String id) {
        return "http://graph.facebook.com/" + id +  "/picture?type=large";
    }
}
