package com.jd.test;


import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import com.jd.test.event.MyEvent;
import com.jd.test.idempotent.OrderParam;
import com.jd.test.idempotent.annotation.Idempotent;
import com.jd.test.idempotent.enums.IdempotentTypeEnum;
import com.jd.test.ob.Observer;
import com.jd.test.ob.Subject;
import com.jd.test.retrylock.annonation.RetryLock;
import com.jd.test.retrylock.enums.RetryLockTypeEnums;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author jd
 * @date 2022/8/2 11:50
 */
@EnableScheduling
@SpringBootApplication
@EnableBinding({Source.class, Sink.class})
@EnableDynamicThreadPool
@RestController
public class App {
    @Resource
    private Subject subject;
    @Resource
    private Observer concreteObserverOne;

    @Resource
    private Observer concreteObserverTwo;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("test")
    @RetryLock(type = RetryLockTypeEnums.PARAM, message = "已创建，请稍后再试")
    public String test(@RequestParam String param) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return param;

    }
}
