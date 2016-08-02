package common.basic.logs;

public enum Level {
    Verbose,
    Debug,
    Info,
    Warn,
    Error,
    Assert;

    private final int length;

    Level() {
        this.length = this.toString().length();
    }

    public int getLength() {
        return length;
    }

    public boolean isLoggable(Level level) {
        return ordinal() >= level.ordinal();
    }
}
