package com.jd.test.ob;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jd
 * @date 2022/8/2 15:55
 */
@Component
public class ConcreteSubject implements Subject, InitializingBean, InstantiationAwareBeanPostProcessor {

    @Resource
    private ApplicationContext applicationContext;

    private final List<Observer> observerList = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observerList.add(observer);

    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notify(String message) {

        observerList.forEach(i -> i.update(message));
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, Observer> beansOfType = applicationContext.getBeansOfType(Observer.class);
        beansOfType.forEach((key, val) -> this.register(val));
    }
}
