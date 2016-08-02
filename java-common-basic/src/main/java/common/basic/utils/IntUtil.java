package common.basic.utils;

import java.util.ArrayList;
import java.util.List;

public class IntUtil {

    public IntUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    public static List<Integer> generateListRange(int until) {
        return generateListRange(0, until);
    }

    private static List<Integer> generateListRange(int from, int until) {
        List<Integer> list = new ArrayList<Integer>(until - from);
        for (int i = from; i < until; i++) {
            list.add(i);
        }

        return list;
    }
}
