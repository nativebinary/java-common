package common.practices2014;

public class ParseHex {

    public static long parseHex(String hexString) throws Exception {

        if (null == hexString || 0 == hexString.length())
            throw new Exception("empty string");

        if (16 < hexString.length())
            throw new Exception("too long string");

        long value = 0;

        for (int i = 0; i < hexString.length(); ++i) {
            char hexChar = hexString.charAt(i);

            long decimalFromHex;
            if ('0' <= hexChar && hexChar <= '9') {
                decimalFromHex = hexChar - '0';
            } else if ('a' <= hexChar && hexChar <= 'f') {
                decimalFromHex = hexChar - 'a' + 10;
            } else if ('A' <= hexChar && hexChar <= 'F') {
                decimalFromHex = hexChar - 'A' + 10;

            } else {
                throw new Exception("invalid hex string");
            }

            value *= 16;
            value += decimalFromHex;
        }

        return value;
    }

    public static String toHex(long value) {

        if (0 == value)
            return "0";

        StringBuilder hexString = new StringBuilder();
        while (0 != value) {

            long mod = value & 0x000000000000000f;
            char hexValue;
            if (mod < 10) {
                hexValue = (char) ('0' + mod);
            } else {
                hexValue = (char) ('a' + mod - 10);
            }
            hexString.insert(0, hexValue);

            value = (value >> 4) & 0x0fffffffffffffffL;
        }

        return hexString.toString();
    }
}
