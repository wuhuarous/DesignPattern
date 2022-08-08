package com.jd.test;


import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import com.jd.test.event.MyEvent;
import com.jd.test.ob.Observer;
import com.jd.test.ob.Subject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author jd
 * @date 2022/8/2 11:50
 */
@EnableScheduling
@SpringBootApplication
@EnableDynamicThreadPool
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

    @PostConstruct
    public void executor() {

//        subject.notify("你们好吗？");

        applicationEventPublisher.publishEvent(new MyEvent(this, "你们号码"));

    }
}
