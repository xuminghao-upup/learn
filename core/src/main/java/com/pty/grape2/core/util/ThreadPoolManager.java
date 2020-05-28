package com.pty.grape2.core.util;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public final class ThreadPoolManager {

    private static ThreadPoolManager sThreadPoolManager = new ThreadPoolManager();

    /**
     * 线程池维护线程的最少数量
     */
    private static final int SIZE_CORE_POOL = 15;

    /**
     * 线程池维护线程的最大数量
     */
    private static final int SIZE_MAX_POOL = 30;

    /**
     * 线程池单例创建方法
     */
    public static ThreadPoolManager newInstance() {
        return sThreadPoolManager;
    }

    private final ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(
            SIZE_CORE_POOL,
            SIZE_MAX_POOL,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("execute-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    private ThreadPoolManager() {
    }

    public void perpare() {
        if (mThreadPool.isShutdown() && !mThreadPool.prestartCoreThread()) {
            @SuppressWarnings("unused")
            int startThread = mThreadPool.prestartAllCoreThreads();
        }
    }

    public void addExecuteTask(Runnable task) {
        if (task != null) {
            mThreadPool.execute(task);
        }
    }

    protected boolean isTaskEnd() {
        return mThreadPool.getActiveCount() == 0;
    }

    public int getQueue(){
        return mThreadPool.getQueue().size();
    }

    public int getPoolSize(){
        return mThreadPool.getPoolSize();
    }

    public long getCompletedTaskCount(){
        return mThreadPool.getCompletedTaskCount();
    }

    public void shutdown() {
        mThreadPool.shutdown();
    }
}
