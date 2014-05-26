package common.android.utils;

import common.basic.logs.Logger;
import common.basic.utils.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;

import java.io.IOException;

public class AndroidHttpClientUtilException extends Exception {
    private final StatusLine statusLine;
    private final HttpEntity httpEntity;
    private final String content;

    public AndroidHttpClientUtilException(StatusLine statusLine, HttpEntity httpEntity) {
        this.statusLine = statusLine;
        this.httpEntity = httpEntity;
        this.content = initContent();
    }

    public String initContent() {
        try {
            if(httpEntity == null)
                return null;

            final String s = HttpEntityUtil.toString(httpEntity);
            Logger.e(s);
            return s;
        }
        catch (IOException e) {
            Logger.e(e);
            return "";
        }
    }

    public AndroidHttpClientUtilException(IOException e) {
        super(e);
        this.statusLine = null;
        this.httpEntity = null;
        this.content = StringUtil.empty;
    }



    @Override
    public String toString() {
        return "AndroidHttpClientUtilException{" +
                "super=" + super.toString() +
                ", statusLine=" + statusLine +
                ", httpEntity=" + httpEntity +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }
}
