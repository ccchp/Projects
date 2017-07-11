package com.lex.executorservicedemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Lex lex on 2017/3/25.
 */

public class ThreadPollExecutor {

    private static final String TAG = "ThreadPollExecutor";
    /**
     * 1、newFixedThreadPool()  该方法返回一个固定线程数量的线程池，不会创建新的现场，也不会销毁已经创建的线程
     * 2、newCachedThreadPool() 返回一个根据实际情况调整线程池线程数量的线程池，数量不固定，动态调整，可以设置保活时间，空闲时间超过设置销毁
     * 3、newSingleThreadPool() 返回只有一个线程的线程池，多余任务会保存在任务队列里等待线程空闲
     * 4、newScheduledThreadPool() 返回一个可以控制线程池内线程定时或周期性执行某任务的线程池
     * 5、newSingleScheduledThreadPool() 以上面相同，只不过里面只有一个线程
     */


    private static volatile ThreadPollExecutor mThreadPollExecutor = null;
    private ExecutorService mExecutorService = null;

    private ThreadPollExecutor() {

    }

    public static ThreadPollExecutor getIntance() {
        if (mThreadPollExecutor == null) {
            synchronized (ThreadPollExecutor.class) {
                if (mThreadPollExecutor == null) {
                    mThreadPollExecutor = new ThreadPollExecutor();
                }
            }
        }
        return mThreadPollExecutor;
    }

    public ExecutorService newFixedThreadPool() {
        return Executors.newFixedThreadPool(3);
    }

    public ExecutorService newCachedThreadPool() {
        return Executors.newCachedThreadPool();
    }

    public ExecutorService newSingleThreadPool() {
        return Executors.newSingleThreadExecutor();
    }

    public ScheduledExecutorService newScheduleThreadPool() {
        return Executors.newScheduledThreadPool(3);
    }

    public ScheduledExecutorService newSingleScheduleThreadPool() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
