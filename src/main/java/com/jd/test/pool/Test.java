package com.jd.test.pool;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jd
 * @date 2022/8/3 15:41
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(Runtime.getRuntime().availableProcessors());
//        ExecutorService threadPool = new ThreadPoolExecutor(
//                5,
//                10,
//                10,
//                TimeUnit.DAYS,
//                new SynchronousQueue<>());

//        Future<String> submit = threadPool.submit(new Tclass("threadpool"));

//        System.out.println(submit.get());

//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            threadPool.execute(()->{
//                get(finalI);
//            });
//        }
//        System.out.println(atomicInteger.get());

//        FutureTask<String> futureTask = new FutureTask<>(new Tclass("threadTask"));
//
//        new Thread().start();
//
//        System.out.println(futureTask.get());

    }

    public static void get(int a) {
        System.out.println(a);
    }
}