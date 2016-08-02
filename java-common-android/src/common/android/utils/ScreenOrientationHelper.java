package common.android.utils;

public class ScreenOrientationHelper {
    public static boolean isLandscape(int rotation) {
        return (rotation == 1 || rotation == 3);
    }
    public static boolean isPortrait(int rotation) {
        return !isLandscape(rotation);
    }
}
