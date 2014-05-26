package common.basic.utils;

import java.util.Arrays;

public class ArrayUtil {

    ArrayUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static <T> boolean isNullOrEmpty(T[] array) {
        return array == null || array.length == 0;
    }
	
	// TODO: generic
    public static boolean startsWith(String[] array1, String[] array2) {
        if(array1.length < array2.length)
            return false;

        for (int i = 0; i < array2.length; i++) {
            if(!StringUtil.equals(array1[i], array2[i]))
                return false;
        }

        return true;


    }

    public static <T> T[] isNull(T[] array, T[] arrayWhenNull) {
        if(array == null)
            return arrayWhenNull;

        return array;
    }

    public static <T> T[] Unbox(Object value, Class<T[]> clazz) {
        if(!clazz.isArray())
            return null;

        return (T[])value;
    }

    //<editor-fold desc="public static T[] slice(array, i, length)">
    public static <T> byte[] slice(byte[] array, int i, int length) {
        return Arrays.copyOfRange(array, i, i + length);
    }

    public static <T> short[] slice(short[] array, int i, int length) {
        return Arrays.copyOfRange(array, i, i + length);
    }

    public static <T> int[] slice(int[] array, int i, int length) {
        return Arrays.copyOfRange(array, i, i + length);
    }

    public static <T> long[] slice(long[] array, int i, int length) {
        return Arrays.copyOfRange(array, i, i + length);
    }

    public static <T> char[] slice(char[] array, int i, int length) {
        return Arrays.copyOfRange(array, i, i + length);
    }

    public static <T> float[] slice(float[] array, int i, int length) {
        return Arrays.copyOfRange(array, i, i + length);
    }

    public static <T> boolean[] slice(boolean[] array, int i, int length) {
        return Arrays.copyOfRange(array, i, i + length);
    }

    public static <T, U> T[] slice(U[] array, int i, int length, Class<? extends T[]> clazz) {
        return Arrays.copyOfRange(array, i, i + length, clazz);
    }
    //</editor-fold>
}
