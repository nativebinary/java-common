package common.android.extensions;

import common.basic.interfaces.ICallbackProgress;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilterOutputStreamProgress extends FilterOutputStream
{

    private final ICallbackProgress callbackProgress;
    private long progress;
    private final long max;

    public FilterOutputStreamProgress(final OutputStream out, long max, final ICallbackProgress callbackProgress)
    {
        super(out);
        this.callbackProgress = callbackProgress;
        this.max = max;
        this.progress = 0;
    }

    public void write(byte[] b, int off, int len) throws IOException
    {
        out.write(b, off, len);
        this.progress += len;
        this.callbackProgress.callback(this.progress, max);
    }

    public void write(int b) throws IOException
    {
        out.write(b);
        this.progress++;
        this.callbackProgress.callback(this.progress, max);
    }
}
