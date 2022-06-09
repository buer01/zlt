package com.zlt.xxe.utils;


import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.*;

/**
 * @date: 2018/7/12
 * @description:
 */
public class ThreadPoolUtils {

    private static int corePoolSize = 200;

    private static int taskPoolSize = 20;

    private static int maxPoolSize = 2000;

    private static long keepAliveTime = 1;

    private static TimeUnit timeUnit = TimeUnit.MINUTES;

    private static BlockingQueue<Runnable> queue = new SynchronousQueue<>();

    private static ExecutorService executorService;

    private static ScheduledExecutorService scheduledExecutorService;

    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ThreadPoolUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ExecutorService getThread() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, queue);
        }
        return executorService;
    }

    public static ScheduledExecutorService getSchedule() {
        if (scheduledExecutorService == null) {
            scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);
        }
        return scheduledExecutorService;
    }

    public static ThreadPoolTaskScheduler getTaskSchedule() {
        if (threadPoolTaskScheduler == null) {
            threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(taskPoolSize);
            threadPoolTaskScheduler.initialize();
        }
        return threadPoolTaskScheduler;
    }
}
