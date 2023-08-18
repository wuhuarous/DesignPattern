package com.jd.test.jdkapi;



import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jd
 * @date 2023/8/17 10:08
 */
public class SupportAbortPolicyRejected implements RejectedExecutionHandler {


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        System.out.println("我拒绝了");
    }


}
