package common.basic.utils;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {
    EncodingUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String substringWithEucKrByteIndex(String s, int i, int length) throws UnsupportedEncodingException {
        final byte[] arrayByte = s.getBytes("EUC-KR");
        return substringWithEucKrByteIndex(arrayByte, i, length);
    }

    public static String substringWithEucKrByteIndex(byte[] arrayByte, int i, int length) throws UnsupportedEncodingException {
        return new String(ArrayUtil.slice(arrayByte, i, length), "EUC-KR");
    }
}
