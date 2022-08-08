package com.jd.test.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jd
 * @date 2022/8/8 14:46
 */
public class GamePlayIH implements InvocationHandler {
    //被代理者
    Class cls = null;
    //被代理者实例
    Object obj = null;

    //我要代理谁
    public GamePlayIH(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object invoke = method.invoke(this.obj, args);
        return invoke;
    }
}
