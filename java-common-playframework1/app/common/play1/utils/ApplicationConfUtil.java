package common.play1.utils;

public class ApplicationConfUtil {
    public static String getProperty(String key) {
        return play.Play.configuration.getProperty(key);
    }
}
