package common.basic.utils;

import common.basic.logs.Logger;

public class ByteUtil {

    @Deprecated // use toIntThrows()
    public static int toInt(byte[] arrayByte){
        Logger.e("use toIntThrows()");
        if (arrayByte.length != 4)
            return -1;

        return ((0x000000FF & (int)arrayByte[0]) << 24
                | (0x000000FF & (int)arrayByte[1]) << 16
                | (0x000000FF & (int)arrayByte[2]) << 8
                | (0x000000FF & (int)arrayByte[3]));
    }

    public static int toIntThrows(byte[] arrayByte) {
        if (arrayByte.length != 4)
            throw new IllegalArgumentException();

        return ((0x000000FF & (int)arrayByte[0]) << 24
                | (0x000000FF & (int)arrayByte[1]) << 16
                | (0x000000FF & (int)arrayByte[2]) << 8
                | (0x000000FF & (int)arrayByte[3]));
    }



    public ByteUtil() throws InstantiationException {
        throw new InstantiationException();
    }


}
