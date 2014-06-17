package common.basic.utils;

import common.basic.logs.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    IpUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static byte[] toArrayByte(String ip) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            return inetAddress.getAddress();
        }
        catch (UnknownHostException e) {
            Logger.e(e);
            return null;
        }
    }

    public static long toInt(String ip) {
        return ByteUtil.toInt(toArrayByte(ip));
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
