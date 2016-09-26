package common.basic.utils;

public class DoubleUtil {
    public DoubleUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static double parse(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    public static double parse(String value) {
        return parse(value, 0);
    }

}
