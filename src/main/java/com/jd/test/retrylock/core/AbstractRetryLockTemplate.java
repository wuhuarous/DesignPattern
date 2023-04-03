package com.jd.test.retrylock.core;


import com.jd.test.retrylock.annonation.RetryLock;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jd
 * @date 2023/3/22 11:21
 */
public abstract class AbstractRetryLockTemplate implements RetryLockExcuteHandler{


    public abstract RetryLockBuildWrapper buildWrapper(RetryLock retryLock,ProceedingJoinPoint joinPoint);

    @Override
    public void execute(RetryLock retryLock, ProceedingJoinPoint joinPoint) {

        RetryLockBuildWrapper retryLockBuildWrapper = buildWrapper(retryLock, joinPoint);
        handler(retryLockBuildWrapper);
    }
}
