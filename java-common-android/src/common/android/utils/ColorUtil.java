package common.android.utils;

import android.graphics.Color;

public class ColorUtil {
    public static int fromHSV(int alpha0to255, float hue0to359, float saturation0to1, float value0to1) {
        return Color.HSVToColor(alpha0to255, new float[]{hue0to359, saturation0to1, value0to1});
    }

    public static int fromHSV(float hue0to359, float saturation0to1, float value0to1) {
        return fromHSV(0xff, hue0to359, saturation0to1, value0to1);
    }
}
