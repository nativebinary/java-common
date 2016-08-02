package common.android.extensions;

import common.android.utils.HttpEntityUtil;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class MultipartEntityProgress extends MultipartEntity
{

    private final HttpEntityUtil.ICallbackToFile callbackToFile;

    public MultipartEntityProgress(final HttpEntityUtil.ICallbackToFile callbackToFile)
    {
        super();
        this.callbackToFile = callbackToFile;
    }

    public MultipartEntityProgress(final HttpMultipartMode mode, HttpEntityUtil.ICallbackToFile callbackToFile)
    {
        super(mode);
        this.callbackToFile = callbackToFile;
    }

    public MultipartEntityProgress(HttpMultipartMode mode, final String boundary, final Charset charset, final HttpEntityUtil.ICallbackToFile callbackToFile)
    {
        super(mode, boundary, charset);
        this.callbackToFile = callbackToFile;
    }

    @Override
    public void writeTo(final OutputStream outputStream) throws IOException
    {
        super.writeTo(new FilterOutputStreamProgress(outputStream, getContentLength(), this.callbackToFile));
    }
}
