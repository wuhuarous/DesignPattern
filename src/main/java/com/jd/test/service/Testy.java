package com.jd.test.service;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jd
 * @date 2022/8/2 11:53
 */
@Component
public class Testy {

    @Resource
    private ThreadPoolExecutor messageConsumeDynamicExecutor;


    @Resource
    private ThreadPoolExecutor messageProduceDynamicExecutor;

    @Resource
    private ThreadPoolExecutor messageProduce1DynamicExecutor;

//    @Scheduled(cron = "0/20 * * * * ?")
    public void  test(){
        for (int i =0 ; i<=10;i++){
            int finalI = i;
            messageProduce1DynamicExecutor.execute(() -> System.out.println(Thread.currentThread() +","+ finalI));

        }

    }

}
