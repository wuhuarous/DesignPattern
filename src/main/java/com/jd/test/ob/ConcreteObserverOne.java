package com.jd.test.ob;


import org.springframework.stereotype.Component;

/**
 * @author jd
 * @date 2022/8/2 15:58
 */
@Component
public class ConcreteObserverOne implements  Observer{
    @Override
    public void update(String message) {
        System.out.println(message+ ":one");
    }
}
