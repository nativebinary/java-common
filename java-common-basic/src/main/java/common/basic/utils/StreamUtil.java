package common.basic.utils;

import common.basic.logs.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class StreamUtil {
    StreamUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static byte[] getArrayByteAll(InputStream inputStream) throws IOException {
        final int sizeBuffer = 16384;

        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] data = new byte[sizeBuffer];
            int countRead;

            while ((countRead = inputStream.read(data, 0, data.length)) != -1)
            {
                byteArrayOutputStream.write(data, 0, countRead);
            }

            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        finally {
            CloseableUtil.close(byteArrayOutputStream);
        }
    }

    public static List<String> getListStringAll(InputStream inputStream, String encoding) throws IOException {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            bufferedReader = new BufferedReader(inputStreamReader);
            return BufferedReaderUtil.getListStringAll(bufferedReader);
        }
        finally {
            CloseableUtil.close(bufferedReader);
            CloseableUtil.close(inputStreamReader);
        }
    }

    public static List<String> getListStringAll(InputStream inputStream) throws IOException {
        return getListStringAll(inputStream, "UTF-8");
    }

    public static String getString(InputStream inputStream, String encoding) throws IOException {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            bufferedReader = new BufferedReader(inputStreamReader);
            return BufferedReaderUtil.getStringAll(bufferedReader);
        }
        finally {
            CloseableUtil.close(bufferedReader);
            CloseableUtil.close(inputStreamReader);
        }

    }

    public static String getString(InputStream inputStream) throws IOException {
        return getString(inputStream, "UTF-8");
    }

    public static boolean copy(InputStream inputStream, OutputStream outputStream) {
        try {
            copyThrows(inputStream, outputStream, null);
            return true;
        }
        catch (IOException e) {
            Logger.e();
            return false;
        }
    }

    public static InputStream createInputStream(String s) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(s.getBytes("UTF-8"));
    }

    public interface ICallbackCopyThrows {
        void notify(long progress);
        boolean isInterruptRequested();
    }

    public static class IOExceptionUserCanceled extends IOException {
        public IOExceptionUserCanceled() {
            super("UserCanceled");
        }
    }

    public static void copyThrows(InputStream inputStream, OutputStream outputStream, ICallbackCopyThrows callbackCopyThrows) throws IOException {
        byte[] arrayByte = new byte[1024 * 64];
        long progress = 0;
        while(true){
            int length = inputStream.read(arrayByte);
            if(length <= 0)
                break;

            outputStream.write(arrayByte, 0, length);
            progress += length;

            if(callbackCopyThrows == null)
                continue;

            callbackCopyThrows.notify(progress);
            if(callbackCopyThrows.isInterruptRequested())
                throw new IOExceptionUserCanceled();
        }
    }
}
