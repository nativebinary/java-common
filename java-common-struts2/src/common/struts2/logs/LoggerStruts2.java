package common.struts2.logs;

import common.basic.logs.ILogger;
import common.basic.logs.Level;
import common.basic.logs.LogUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerStruts2 implements ILogger {
    private boolean debug = false;
    private Level level = Level.Verbose;
    private final int stackRewindCount = 4;
    private final Log log = LogFactory.getLog("STRUTS2");

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
        return debug && level.isLoggable(this.level);
    }

    @Override
    public void v(Object... arrayObject) {
        if(!isLoggable(Level.Verbose))
            return;

        log.trace(LogUtil.makeMessage(arrayObject, Level.Verbose, stackRewindCount));
    }

    @Override
    public void d(Object... arrayObject) {
        if(!isLoggable(Level.Debug))
            return;

        log.debug(LogUtil.makeMessage(arrayObject, Level.Debug, stackRewindCount));
    }

    @Override
    public void i(Object... arrayObject) {
        if(!isLoggable(Level.Info))
            return;

        log.info(LogUtil.makeMessage(arrayObject, Level.Info, stackRewindCount));
    }

    @Override
    public void w(Object... arrayObject) {
        if(!isLoggable(Level.Warn))
            return;

        log.warn(LogUtil.makeMessage(arrayObject, Level.Warn, stackRewindCount));
    }

    @Override
    public void e(Object... arrayObject) {
        if(!isLoggable(Level.Error))
            return;

        log.error(LogUtil.makeMessage(arrayObject, Level.Error, stackRewindCount));
    }


    @Override
    public void rv(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Verbose))
            return;

        log.trace(LogUtil.makeMessage(arrayObject, Level.Verbose, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void rd(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Debug))
            return;

        log.debug(LogUtil.makeMessage(arrayObject, Level.Debug, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void ri(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Info))
            return;

        log.info(LogUtil.makeMessage(arrayObject, Level.Info, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void rw(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Warn))
            return;

        log.warn(LogUtil.makeMessage(arrayObject, Level.Warn, stackRewindCount + additionalStackRewindCount));
    }

    @Override
    public void re(int additionalStackRewindCount, Object... arrayObject) {
        if(!isLoggable(Level.Error))
            return;

        log.error(LogUtil.makeMessage(arrayObject, Level.Error, stackRewindCount + additionalStackRewindCount));
    }

}
