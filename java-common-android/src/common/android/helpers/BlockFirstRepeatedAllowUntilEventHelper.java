package common.android.helpers;

public class BlockFirstRepeatedAllowUntilEventHelper {
    private static final long milliSecBlockFirstRepeated = 500;
    private static final long milliSecAllowUntil = 2000;

    private long milliSecLastEvent = 0;

    public boolean isBlocked() {
        long time = System.currentTimeMillis();
        long duration = time - milliSecLastEvent;

        milliSecLastEvent = time;
        return !(milliSecBlockFirstRepeated <= duration && duration <= milliSecAllowUntil);
    }
}
