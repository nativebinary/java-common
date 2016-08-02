package common.basic.utils;

import java.util.zip.CRC32;

public class CrcUtil {

    public CrcUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static long calculate(byte[] data) {
        CRC32 crc = new CRC32();
        crc.update(data);
        return crc.getValue();
    }

}
