package com.jd.test.jdkapi;


import com.jd.test.jdkapi.dyanmic.RejectedExecutionProxyInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jd
 * @date 2023/8/17 9:59
 */
public class SupportThreadPoolExecutor extends ThreadPoolExecutor {

    private final AtomicInteger rejectCount = new AtomicInteger();

    public SupportThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        RejectedExecutionHandler rejectedExecutionHandler = (RejectedExecutionHandler) Proxy.newProxyInstance(handler.getClass().getClassLoader(), handler.getClass().getInterfaces(), new RejectedExecutionProxyInvocationHandler(handler, this));
        super.setRejectedExecutionHandler(rejectedExecutionHandler);
    }

    public SupportThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public void incremnetRejectCount() {
        rejectCount.incrementAndGet();
    }

    public int getRejectCount() {
        return rejectCount.get();
    }
}
