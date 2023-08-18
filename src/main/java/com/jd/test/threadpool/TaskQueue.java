package com.jd.test.threadpool;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author jd
 * @date 2023/8/17 17:00
 */
public class TaskQueue<R extends Runnable> extends LinkedBlockingQueue<Runnable> {

    private EagerThreadPoolExecutor executor;

    public TaskQueue(int capacity) {
        super(capacity);
    }

    public void setExecutor(EagerThreadPoolExecutor executor) {
        this.executor = executor;
    }

    /**
     * Inserts the specified element at the tail of this queue if it is
     * possible to do so immediately without exceeding the queue's capacity,
     * returning {@code true} upon success and {@code false} if this queue
     * is full.
     * When using a capacity-restricted queue, this method is generally
     * preferable to method {@link BlockingQueue#add add}, which can fail to
     * insert an element only by throwing an exception.
     *
     * @param runnable
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean offer(Runnable runnable) {

        int currentThreadPoolSize = executor.getPoolSize();
        if (executor.getSubmittedTaskCount() < currentThreadPoolSize) {
            return super.offer(runnable);
        }
        if (currentThreadPoolSize < executor.getMaximumPoolSize()) {
            return false;
        }
        return super.offer(runnable);
    }

    public boolean retryOffer(Runnable r, long timeout, TimeUnit unit) {
        if (executor.isShutdown()) {
            throw new RejectedExecutionException("Executor is shutdown!");
        }
        try {
            return super.offer(r, timeout, unit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
