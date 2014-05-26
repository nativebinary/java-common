package common.android.logs;

import android.util.Log;
import common.basic.logs.ILogger;
import common.basic.logs.Level;
import common.basic.logs.LogUtil;

public class LoggerAndroid implements ILogger {

    private final String tag;
    private boolean debug = false;
    private int stackRewindCount = 5;

    public LoggerAndroid() {
        this("NB");
    }

    public LoggerAndroid(String tag) {
        this.tag = tag;
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public boolean isDebug() {
        return debug;
    }

    @Override
    public boolean isLoggable(Level level) {
        return debug && Log.isLoggable(tag, getAndroidLogLevel(level));
    }

    private int getAndroidLogLevel(Level level) {
        switch (level) {
            case Verbose: return Log.VERBOSE;
            case Debug: return Log.VERBOSE;
            case Info: return Log.VERBOSE;
            case Warn: return Log.VERBOSE;
            case Error: return Log.VERBOSE;
            case Assert: return Log.VERBOSE;
        }

        return Log.VERBOSE;
    }


    @Override
    public void v(Object... arrayObject) {
        if(!isLoggable(Level.Verbose))
            return;

        Log.v(tag, LogUtil.makeMessage(arrayObject, Level.Verbose, stackRewindCount));
    }

    @Override
    public void d(Object... arrayObject) {
        if(!isLoggable(Level.Debug))
            return;

        Log.d(tag, LogUtil.makeMessage(arrayObject, Level.Debug, stackRewindCount));
    }

    @Override
    public void i(Object... arrayObject) {
        if(!isLoggable(Level.Info))
            return;

        Log.i(tag, LogUtil.makeMessage(arrayObject, Level.Info, stackRewindCount));
    }

    @Override
    public void w(Object... arrayObject) {
        if(!isLoggable(Level.Warn))
            return;

        Log.w(tag, LogUtil.makeMessage(arrayObject, Level.Warn, stackRewindCount));
    }

    @Override
    public void e(Object... arrayObject) {
        if(!isLoggable(Level.Error))
            return;

        Log.e(tag, LogUtil.makeMessage(arrayObject, Level.Error, stackRewindCount));
    }



    @Override
    public void rv(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Verbose))
            return;

        Log.v(tag, LogUtil.makeMessage(arrayObject, Level.Verbose, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void rd(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Debug))
            return;

        Log.d(tag, LogUtil.makeMessage(arrayObject, Level.Debug, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void ri(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Info))
            return;

        Log.i(tag, LogUtil.makeMessage(arrayObject, Level.Info, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void rw(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Warn))
            return;

        Log.w(tag, LogUtil.makeMessage(arrayObject, Level.Warn, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void re(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Error))
            return;

        Log.e(tag, LogUtil.makeMessage(arrayObject, Level.Error, stackRewindCount + additionalStackRewindCount));
    }


}
