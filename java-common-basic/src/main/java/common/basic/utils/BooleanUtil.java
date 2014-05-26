package common.basic.utils;

public class BooleanUtil {

    BooleanUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static boolean parse(String value) {
        if (StringUtil.isNullOrEmpty(value))
            return false;

        String valueLowerCase = value.toLowerCase();
        return StringUtil.equals("true", valueLowerCase)
                || StringUtil.equals("1", valueLowerCase)
                || StringUtil.equals("y", valueLowerCase)
                || StringUtil.equals("yes", valueLowerCase)
                || StringUtil.equals("t", valueLowerCase);
    }

    public static String toString(boolean value)
    {
        return value ? "true" : "false";
    }
	
    public static String convert01(boolean defaultValue) {
        return defaultValue ? "1" : "0";
    }

    public static boolean convert01(String v) {
        return StringUtil.equals("1", v);
    }

    public static String convertYN(boolean value) {
        return value ? "Y" : "N";
    }

    public static boolean convertYN(String value) {
        return StringUtil.equals("Y", value);
    }
}
