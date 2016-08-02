package common.basic.helpers;

import common.basic.logs.Logger;
import common.basic.utils.StringUtil;
import common.basic.utils.ThreadUtil;

public class MemoryWatcher {
    private static boolean running = false;
    private static String percentBefore = "00.00%";
    public static void start() {
        Logger.e();

        final Thread thread = ThreadUtil.createBackgroundThread(MemoryWatcher.class.getSimpleName(), new Runnable() {
            @Override
            public void run() {
                running = true;
                percentBefore = "00.0%";

                while(running) {
                    try {
                        log();
                    }
                    finally {
                        ThreadUtil.sleep(1000);
                    }
                }
            }
        });
        thread.start();
    }

    public static void log() {
        final Runtime runtime = Runtime.getRuntime();
        final long maxMemory = runtime.maxMemory();
        final long totalMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        final long allocatedMemory = totalMemory - freeMemory;

        final String allocMemMB = String.format("%,dKB", allocatedMemory / 1024);
        final String maxMemMB = String.format("%,dKB", maxMemory / 1024);
        final String percent = String.format("%.1f%%", allocatedMemory * 100d / maxMemory);
        if (!StringUtil.equals(percentBefore, percent)) {
            Logger.i(allocMemMB, maxMemMB, percentBefore, "->", percent);
            percentBefore = percent;
        }
    }

    public static void stop() {
        Logger.e();

        running = false;
    }
}
