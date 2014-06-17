package common.android.extensions;

import common.android.utils.HttpEntityUtil;
import common.basic.utils.StreamUtil;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilterOutputStreamProgress extends FilterOutputStream
{

    private final HttpEntityUtil.ICallbackToFile callbackToFile;
    private long progress;
    private final long max;

    public FilterOutputStreamProgress(final OutputStream out, long max, HttpEntityUtil.ICallbackToFile callbackToFile)
    {
        super(out);
        this.callbackToFile = callbackToFile;
        this.max = max;
        this.progress = 0;
    }

    public void write(byte[] b, int off, int len) throws IOException
    {
        if(callbackToFile.isInterruptRequested()) {
            throw new StreamUtil.IOExceptionUserCanceled();
        }
        out.write(b, off, len);
        this.progress += len;

        this.callbackToFile.callback(this.progress, max);
    }

    public void write(int b) throws IOException
    {
        if(callbackToFile.isInterruptRequested())
            throw new StreamUtil.IOExceptionUserCanceled();

        out.write(b);
        this.progress++;
        this.callbackToFile.callback(this.progress, max);
    }
}
