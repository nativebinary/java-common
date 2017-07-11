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

    public static BigDecimal parse(long value) {
        return new BigDecimal(value);
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




    public static boolean isZero(BigDecimal value) {
        return equals(BigDecimal.ZERO, value);
    }

    public static boolean isMinus(BigDecimal value) {
        return BigDecimal.ZERO.compareTo(value) > 0;
    }

    public static boolean isPlus(BigDecimal value) {
        return BigDecimal.ZERO.compareTo(value) < 0;
    }

    public static boolean isZeroOrMinus(BigDecimal value) {
        return isZero(value) || isMinus(value);
    }

    public static boolean isZeroOrPlus(BigDecimal value) {
        return isZero(value) || isPlus(value);
    }




    public static boolean lt(BigDecimal lf, BigDecimal rf) {
        return lf.compareTo(rf) < 0;
    }

    public static boolean lt(BigDecimal lf, long rf) {
        return lt(lf, new BigDecimal(rf));
    }

    public static boolean lt(long lf, BigDecimal rf) {
        return lt(new BigDecimal(lf), rf);
    }

    public static boolean lt(BigDecimal lf, String rf) {
        return lt(lf, new BigDecimal(rf));
    }

    public static boolean lt(String lf, BigDecimal rf) {
        return lt(new BigDecimal(lf), rf);
    }



    public static boolean le(BigDecimal lf, BigDecimal rf) {
        return lf.compareTo(rf) <= 0;
    }

    public static boolean le(BigDecimal lf, long rf) {
        return le(lf, new BigDecimal(rf));
    }

    public static boolean le(long lf, BigDecimal rf) {
        return le(new BigDecimal(lf), rf);
    }

    public static boolean le(BigDecimal lf, String rf) {
        return le(lf, new BigDecimal(rf));
    }

    public static boolean le(String lf, BigDecimal rf) {
        return le(new BigDecimal(lf), rf);
    }




    public static boolean gt(BigDecimal lf, BigDecimal rf) {
        return lf.compareTo(rf) > 0;
    }

    public static boolean gt(BigDecimal lf, long rf) {
        return gt(lf, new BigDecimal(rf));
    }

    public static boolean gt(long lf, BigDecimal rf) {
        return gt(new BigDecimal(lf), rf);
    }

    public static boolean gt(BigDecimal lf, String rf) {
        return gt(lf, new BigDecimal(rf));
    }

    public static boolean gt(String lf, BigDecimal rf) {
        return gt(new BigDecimal(lf), rf);
    }




    public static boolean ge(BigDecimal lf, BigDecimal rf) {
        return lf.compareTo(rf) >= 0 ;
    }

    public static boolean ge(BigDecimal lf, long rf) {
        return ge(lf, new BigDecimal(rf));
    }

    public static boolean ge(long lf, BigDecimal rf) {
        return ge(new BigDecimal(lf), rf);
    }

    public static boolean ge(BigDecimal lf, String rf) {
        return ge(lf, new BigDecimal(rf));
    }

    public static boolean ge(String lf, BigDecimal rf) {
        return ge(new BigDecimal(lf), rf);
    }


    public static boolean notEquals(BigDecimal lf, BigDecimal rf) {
        if (null == lf || null == rf)
            return true;

        return 0 != lf.compareTo(rf);
    }

    public static boolean notEquals(long lf, BigDecimal rf) {
        return notEquals(new BigDecimal(lf), rf);
    }

    public static boolean notEquals(BigDecimal lf, long rf) {
        return notEquals(lf, new BigDecimal(rf));
    }

    public static boolean notEquals(String lf, BigDecimal rf) {
        return notEquals(new BigDecimal(lf), rf);
    }

    public static boolean notEquals(BigDecimal lf, String rf) {
        return notEquals(lf, new BigDecimal(rf));
    }

    public static boolean notEquals(String lf, String rf) {
        return notEquals(new BigDecimal(lf), new BigDecimal(rf));
    }

    public static boolean equals(BigDecimal lf, BigDecimal rf) {
        if (null == lf || null == rf)
            return false;

        return 0 == lf.compareTo(rf);
    }

    public static boolean equals(long lf, BigDecimal rf) {
        return equals(new BigDecimal(lf), rf);
    }

    public static boolean equals(BigDecimal lf, long rf) {
        return equals(lf, new BigDecimal(rf));
    }

    public static boolean equals(String lf, BigDecimal rf) {
        return equals(new BigDecimal(lf), rf);
    }

    public static boolean equals(BigDecimal lf, String rf) {
        return equals(lf, new BigDecimal(rf));
    }

    public static boolean equals(String lf, String rf) {
        return equals(new BigDecimal(lf), new BigDecimal(rf));
    }



}
