package com.jd.test.retrylock.core;


import com.jd.test.idempotent.core.IdempotentContext;
import com.jd.test.retrylock.annonation.RetryLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author jd
 * @date 2023/3/22 11:40
 */
@Aspect
public class RetryLockAspect {


    @Around("@annotation(com.jd.test.retrylock.annonation.RetryLock)")
    public Object retryLockHandler(ProceedingJoinPoint joinPoint) {
        RetryLock lock = getRetryLock(joinPoint);
        RetryLockExcuteHandler instance = RetryLockExecuteHandlerForctory.getInstance(lock.scene(),lock.type());
        instance.execute(lock,joinPoint);
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }finally {
            instance.postProcessing();
            IdempotentContext.clean();
        }

    }

    private RetryLock getRetryLock(ProceedingJoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(RetryLock.class);
    }
}
