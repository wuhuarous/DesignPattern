package com.jd.test.jdkapi.dyanmic;


import com.jd.test.jdkapi.SupportThreadPoolExecutor;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author jd
 * @date 2023/8/17 10:40
 */
@AllArgsConstructor
public class RejectedExecutionProxyInvocationHandler implements InvocationHandler {

    private RejectedExecutionHandler targer;

    private SupportThreadPoolExecutor executor;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        executor.incremnetRejectCount();
        return method.invoke(targer,args);
    }
}
