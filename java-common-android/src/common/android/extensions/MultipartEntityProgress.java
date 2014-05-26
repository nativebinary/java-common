package common.android.extensions;

import common.basic.interfaces.ICallbackProgress;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class MultipartEntityProgress extends MultipartEntity
{

    private final ICallbackProgress callbackProgress;

    public MultipartEntityProgress(final ICallbackProgress callbackProgress)
    {
        super();
        this.callbackProgress = callbackProgress;
    }

    public MultipartEntityProgress(final HttpMultipartMode mode, final ICallbackProgress callbackProgress)
    {
        super(mode);
        this.callbackProgress = callbackProgress;
    }

    public MultipartEntityProgress(HttpMultipartMode mode, final String boundary, final Charset charset, final ICallbackProgress callbackProgress)
    {
        super(mode, boundary, charset);
        this.callbackProgress = callbackProgress;
    }

    @Override
    public void writeTo(final OutputStream outputStream) throws IOException
    {
        super.writeTo(new FilterOutputStreamProgress(outputStream, getContentLength(), this.callbackProgress));
    }
}
