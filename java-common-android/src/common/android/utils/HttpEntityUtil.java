package common.android.utils;

import common.android.extensions.FilterOutputStreamProgress;
import common.basic.interfaces.ICallbackProgress;
import common.basic.utils.CloseableUtil;
import common.basic.utils.StreamUtil;
import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

	// TODO: check
    public interface ICallbackToFile {
        void callback(long progress, long contentLength);
        boolean isInterruptRequested();
    }

    static void toFile(HttpEntity httpEntity, File file, final ICallbackToFile callbackToFile) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            final InputStream inputStream = httpEntity.getContent();
            final long contentLength = httpEntity.getContentLength();
            StreamUtil.copyThrows(inputStream, fileOutputStream, new StreamUtil.ICallbackCopyThrows() {
                @Override
                public void notify(long progress) {
                    callbackToFile.callback(progress, contentLength);
                }

                @Override
                public boolean isInterruptRequested() {
                    return callbackToFile.isInterruptRequested();
                }
            });
        }
        finally {
            CloseableUtil.close(fileOutputStream);
        }
    }	
}
