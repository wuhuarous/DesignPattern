package com.jd.test.jdkapi.demo;


import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jd
 * @date 2023/8/17 11:11
 */
@AllArgsConstructor
public class TestInvocationHandler implements InvocationHandler {

    private TestInterface testInterface;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始前");
        Object invoke = method.invoke(testInterface, args);
        return invoke;
    }
}
