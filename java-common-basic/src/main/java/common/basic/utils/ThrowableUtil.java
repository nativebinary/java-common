package common.basic.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ThrowableUtil {

    ThrowableUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String getStackTrace(Throwable throwable) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PrintStream printStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            printStream = new PrintStream(byteArrayOutputStream);
            throwable.printStackTrace(printStream);
            return byteArrayOutputStream.toString("UTF-8"); // e.g. ISO-8859-1
        }
        catch (UnsupportedEncodingException e) {
            return e.toString();
        }
        finally {
            CloseableUtil.close(printStream);
            CloseableUtil.close(byteArrayOutputStream);
        }
    }
}
