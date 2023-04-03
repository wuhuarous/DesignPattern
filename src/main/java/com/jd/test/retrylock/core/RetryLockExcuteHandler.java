package com.jd.test.retrylock.core;


import com.jd.test.retrylock.annonation.RetryLock;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jd
 * @date 2023/3/22 11:17
 */
public interface RetryLockExcuteHandler {


    void handler(RetryLockBuildWrapper buildWrapper);


    void execute(RetryLock retryLock, ProceedingJoinPoint joinPoint);


    default void postProcessing(){


    }
}
