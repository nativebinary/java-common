package common.android.utils;

import android.graphics.Color;
import android.graphics.Point;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class SerializeUtil {
    public static String serialize(List<Point> listPoint) {

        List<String> listString = new ArrayList<String>();
        for (Point point : listPoint)
            listString.add(serialize(point));

        return "[" + TextUtils.join(",", listString) + "]";
    }

    public static String serialize(Point point) {
        return "(" + point.x + "," + point.y + ")";
    }

    public static String serializeColor(int color) {
        return String.format("#%08x", color);
    }

    public static int serializeColor(String color) {

//        if (color.startsWith("#"))
//            color = color.substring(1);

        return Color.parseColor(color);
    }

    public static String serialize(float value) {
        return String.format("%.3f", value);
    }

    public static float serializeFloat(String value) {
        return Float.parseFloat(value);
    }

    public static List<Point> serializeListPoint(String value) {

        if (value.length() < 2)
            return new ArrayList<Point>();

        /// 0123456789012
        /// [(1,2),(3,4)]

        String[] arrayPoint = value.substring(2, value.length() - 2).split("\\),\\(");

        List<Point> listPoint = new ArrayList<Point>();

        for (String point : arrayPoint)
            listPoint.add(serializePoint(point));

        return listPoint;
    }

    private static Point serializePoint(String point) {

        final String[] array = point.split(",");

        final int x  = Integer.parseInt(array[0]);
        final int y  = Integer.parseInt(array[1]);
        return new Point(x, y);
    }
}
