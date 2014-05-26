package common.android.utils;

import common.android.extensions.FilterOutputStreamProgress;
import common.basic.interfaces.ICallbackProgress;
import common.basic.utils.CloseableUtil;
import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HttpEntityUtil {
    static String toString(HttpEntity entity) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            entity.writeTo(byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        }
        finally {
            CloseableUtil.close(byteArrayOutputStream);
        }
    }

    static void toFile(HttpEntity httpEntity, File file, final ICallbackProgress callbackProgress) throws IOException {
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream(file);
            httpEntity.writeTo(new FilterOutputStreamProgress(fileOutput, httpEntity.getContentLength(), callbackProgress));
        }
        finally {
            CloseableUtil.close(fileOutput);
        }
    }
}
