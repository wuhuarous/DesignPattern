package com.jd.test.jdkapi;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jd
 * @date 2023/8/17 10:10
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        SupportThreadPoolExecutor  executor = new SupportThreadPoolExecutor(1,1,1024, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1),new SupportAbortPolicyRejected());


        for (int i = 0; i < 10; i++) {
            executor.execute(()-> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.println(String.format("线程池拒绝策略次数 :: %d", executor.getRejectCount()));

    }
}
