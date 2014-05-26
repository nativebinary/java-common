package common.basic.utils;

import sun.net.util.IPAddressUtil;

public class IpUtil {
    public static long ipStringToLong(String ipString) {
        byte[] bytes = IPAddressUtil.textToNumericFormatV4(ipString);

        if (bytes.length != 4)
            return -1;

        return ((0x000000FF & (long)bytes[0]) << 24
                | (0x000000FF & (long)bytes[1]) << 16
                | (0x000000FF & (long)bytes[2]) << 8
                | (0x000000FF & (long)bytes[3]));
    }

    public static long cidrToMask(int cidr) {
        long mask = 0;
        for (int i = 0 ; i < 32; ++i)
        {
            mask <<= 1;
            if (i < cidr)
                mask |= 1;
        }
        return mask;
    }
}
