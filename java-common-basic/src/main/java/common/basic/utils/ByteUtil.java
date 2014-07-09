package common.basic.utils;

public class ByteUtil {

    public static int toInt(byte[] arrayByte){

        if (arrayByte.length != 4)
            return -1;

        return ((0x000000FF & (int)arrayByte[0]) << 24
                | (0x000000FF & (int)arrayByte[1]) << 16
                | (0x000000FF & (int)arrayByte[2]) << 8
                | (0x000000FF & (int)arrayByte[3]));
    }

    public ByteUtil() throws InstantiationException {
        throw new InstantiationException();
    }


}
