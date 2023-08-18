package com.jd.test.threadpool;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jd
 * @date 2023/8/17 17:18
 */
public class Test {

    public static void main(String[] args) {
        TaskQueue<Runnable> runnables = new TaskQueue<>(100);
        EagerThreadPoolExecutor   executor = new EagerThreadPoolExecutor(1,3,100, TimeUnit.SECONDS, runnables,new ThreadPoolExecutor.AbortPolicy());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,3,100,TimeUnit.SECONDS,new LinkedBlockingDeque<>(100));
        runnables.setExecutor(executor);
        for (int i = 0; i < 10; i++) {
            executor.execute(()-> {
                try {
                    System.out.println("1111--"+Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
/*            threadPoolExecutor.execute(()-> {
                try {
                    System.out.println("2222--"+Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });*/


        }
    }
}
