package com.jd.test;


import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import com.alibaba.fastjson.JSONObject;
import com.jd.test.idempotent.annotation.Idempotent;
import com.jd.test.idempotent.enums.IdempotentSceneEnum;
import com.jd.test.idempotent.enums.IdempotentTypeEnum;
import com.jd.test.log.annotation.MLog;
import com.jd.test.ob.Observer;
import com.jd.test.ob.Subject;
import com.jd.test.retrylock.annonation.RetryLock;
import com.jd.test.retrylock.enums.RetryLockTypeEnums;
import lombok.Data;
import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * @author jd
 * @date 2022/8/2 11:50
 */
@EnableScheduling
@SpringBootApplication
@EnableBinding({Source.class, Sink.class})
//@EnableDynamicThreadPool
@RestController
@EsMapperScan("com.jd.test.elastic")
//@EnableEurekaServer
public class App {
    @Resource
    private Subject subject;
    @Resource
    private Observer concreteObserverOne;

    @Resource
    private Observer concreteObserverTwo;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    private final static Semaphore semaphore = new Semaphore(1);


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("test")
    @Idempotent(
            key = "'test_lock_'+#param+'_'+#param.hashCode()",
            type = IdempotentTypeEnum.SPEL,
            scene = IdempotentSceneEnum.RESTAPI
    )
    @MLog
    public String test(@RequestParam String param) {
        try {
            semaphore.acquire();
//            Thread.sleep(10000);
            System.out.println("完成上"+ param);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
        return param;

    }

    @PostMapping("callback")
    @MLog
//    @RetryLock(type = RetryLockTypeEnums.PARAM, message = "已创建，请稍后再试")
    public Params callback(@RequestBody Params param) throws Exception {

        System.out.println(param);
        return param;
    }
    @Data
    static class Params {

        private String challenge;
        private String openId;

        private String userId;
        private Action action;

        private static class Action {

            private Map<String, String> value;

        }

    }

}
