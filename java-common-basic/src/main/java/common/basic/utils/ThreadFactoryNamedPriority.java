package common.basic.utils;

import common.basic.logs.Logger;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryNamedPriority implements ThreadFactory {
    private final String name;
    private final int priority;

    public ThreadFactoryNamedPriority(String name) {
        this(name, Thread.MIN_PRIORITY);
    }

    public ThreadFactoryNamedPriority(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Logger.i(name);
        final Thread thread = ThreadUtil.create(name, runnable);
        thread.setPriority(this.priority);
        return thread;
    }
}
