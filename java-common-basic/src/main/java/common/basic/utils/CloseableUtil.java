package common.basic.utils;

import common.basic.logs.Logger;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtil {

    CloseableUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static boolean close(Closeable closeable) {
        if(closeable == null)
            return false;

        try {
            closeable.close();
            return true;
        }
        catch (IOException e) {
            Logger.e(e);
            return false;
        }
    }
}
