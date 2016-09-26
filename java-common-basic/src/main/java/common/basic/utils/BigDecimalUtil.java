package common.basic.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public static BigDecimal HUNDRED = BigDecimal.valueOf(100);
    public static BigDecimal MINUS_ONE = BigDecimal.valueOf(-1);
    public static BigDecimal TWO = BigDecimal.valueOf(2);;

    public BigDecimalUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static BigDecimal parse(String value, BigDecimal defaultValue) {
        try {
            return new BigDecimal(value);
        } catch (Throwable ignored) {
            return defaultValue;
        }
    }

    public static BigDecimal parse(String value) {
        return parse(value, BigDecimal.ZERO);
    }

    public static boolean gt(BigDecimal lf, BigDecimal rf) {
        return lf.compareTo(rf) > 0;
    }

    public static boolean lt(BigDecimal lf, BigDecimal rf) {
        return lf.compareTo(rf) < 0;
    }

    public static boolean eq(BigDecimal lf, BigDecimal rf) {
        return lf.compareTo(rf) == 0;
    }

    public static boolean isZero(BigDecimal value) {
        return BigDecimal.ZERO.equals(value);
    }

    public static boolean isMinus(BigDecimal value) {
        return BigDecimal.ZERO.compareTo(value) > 0;
    }

    public static boolean isPlus(BigDecimal value) {
        return BigDecimal.ZERO.compareTo(value) < 0;
    }

    public static BigDecimal parseSubString(String value, int start, int end) {
        if (value.length() < end)
            throw new IllegalArgumentException();

        return parse(value.substring(start, end).trim());
    }

    public static BigDecimal parseSubString(String value, int start) {
        if (value.length() < start)
            throw new IllegalArgumentException();

        return parse(value.substring(start).trim());
    }
}
