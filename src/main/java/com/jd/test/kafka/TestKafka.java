package com.jd.test.kafka;


import com.jd.test.kafka.provide.DelayCloseOrderProvide;
import com.jd.test.kafka.provide.DelayCloseOrderProvide2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 *  注意点 一定要通过注解@EnableBing将Sink订阅方和生产方Source绑定
 *  注入MessageChannel 一定要和注解定义好的@output一致
 *
 * @author jd
 * @date 2023/3/16 15:43
 */
@Component
public class TestKafka  {
    @Autowired
   public DelayCloseOrderProvide delayCloseOrderProvide;
    @Autowired
    public DelayCloseOrderProvide2 delayCloseOrderProvide2;
    private final CountDownLatch  countDownLatch = new CountDownLatch(1);


    @Scheduled(cron = "0 * * * * *")
    public void test() {

        DelayCloseOrderEvent  en = new DelayCloseOrderEvent();
//        en.setOrderSn("我是test-kafka");
//        delayCloseOrderProvide.delayCloseOrderSend(en);
        en.setOrderSn("我是my-topic");
        delayCloseOrderProvide2.delayCloseOrderSend(en);
    }


    public  void destory(){
        countDownLatch.countDown();
    }
}
