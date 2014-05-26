package common.basic.logs;

public interface ILogger {
    void setDebug(boolean debug);
    boolean isDebug();
    boolean isLoggable(Level level);
    void v(Object... arrayObject);
    void d(Object... arrayObject);
    void i(Object... arrayObject);
    void w(Object... arrayObject);
    void e(Object... arrayObject);

    void rv(int additionalStackRewindCount, Object... arrayObject);
    void rd(int additionalStackRewindCount, Object... arrayObject);
    void ri(int additionalStackRewindCount, Object... arrayObject);
    void rw(int additionalStackRewindCount, Object... arrayObject);
    void re(int additionalStackRewindCount, Object... arrayObject);
}
