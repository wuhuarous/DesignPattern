package com.jd.test.jdkapi.demo;


/**
 * @author jd
 * @date 2023/8/17 11:13
 */
public class Person implements TestInterface{
    @Override
    public void send() {
        System.out.println("发送消息");
    }

    @Override
    public void exception() {
        System.out.println("异常处理");
    }
}
