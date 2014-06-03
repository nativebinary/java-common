package common.basic.logs;

public class Logger {
    static ILogger logger = new LoggerStandardOut();

    public Logger() throws InstantiationException {
        throw new InstantiationException();
    }

    public static void setLogger(ILogger logger) {
        Logger.logger = logger;
    }

    public static void setDebug(boolean debug) {
        logger.setDebug(debug);
    }

    public static boolean isDebug() {
        return logger.isDebug();
    }

    public static boolean isLoggable(Level level) {
        return logger.isLoggable(level);
    }

    public static void v(Object... arrayObject) {
        logger.v(arrayObject);
    }

    public static void d(Object... arrayObject) {
        logger.d(arrayObject);
    }

    public static void i(Object... arrayObject) {
        logger.i(arrayObject);
    }

    public static void w(Object... arrayObject) {
        logger.w(arrayObject);
    }

    public static void e(Object... arrayObject) {
        logger.e(arrayObject);
    }

    public static void rv(int additionalStackRewindCount, Object... arrayObject) {
        logger.rv(additionalStackRewindCount, arrayObject);
    }

    public static void rd(int additionalStackRewindCount, Object... arrayObject) {
        logger.rd(additionalStackRewindCount, arrayObject);
    }

    public static void ri(int additionalStackRewindCount, Object... arrayObject) {
        logger.ri(additionalStackRewindCount, arrayObject);
    }

    public static void rw(int additionalStackRewindCount, Object... arrayObject) {
        logger.rw(additionalStackRewindCount, arrayObject);
    }

    public static void re(int additionalStackRewindCount, Object... arrayObject) {
        logger.re(additionalStackRewindCount, arrayObject);
    }
}
