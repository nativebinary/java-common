package common.basic.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    public static String decodeBase64(String base64) {
        return new String(Base64.decodeBase64(base64.getBytes()));
    }

    public static String encodeBase64(String value) {
        return new String(Base64.encodeBase64(value.getBytes()));
    }
}
