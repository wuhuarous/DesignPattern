package com.jd.test.jdkapi;


import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jd
 * @date 2023/8/17 10:03
 */
public interface SupportRejectedExecutionHandler extends RejectedExecutionHandler {

    default void beforeReject(ThreadPoolExecutor executor){
        if(executor instanceof  SupportThreadPoolExecutor){
            SupportThreadPoolExecutor supportThreadPoolExecutor = (SupportThreadPoolExecutor) executor;
            supportThreadPoolExecutor.incremnetRejectCount();
            ExceptionHandler.send("11");
//            System.out.println("触发任务拒绝");
        }


    }

}
