package common.basic.utils;

import common.basic.logs.Logger;

public class ThreadUtil {

	
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            Logger.v(Thread.currentThread().getName(), "Interrupted");
        }
    }
	
    public static Thread create(String name, Runnable runnable) {
        return new Thread(runnable, name);
    }
	
	    public static Thread createBackgroundThread(String name, Runnable runnable) {
        final Thread thread = create(name, runnable);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.setDaemon(true);
        return thread;
    }
}
