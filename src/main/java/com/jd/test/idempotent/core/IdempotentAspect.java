package com.jd.test.idempotent.core;


import com.jd.test.idempotent.annotation.Idempotent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author jd
 * @date 2023/3/21 9:48
 */
@Aspect
public class IdempotentAspect {


    @Around("@annotation(com.jd.test.idempotent.annotation.Idempotent)")
    public Object idempotentHandler(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Idempotent idempotent = getIdempotent(joinPoint);
        IdempotentExecuteHandler instance = IdempotentExecuteHandlerFactory.getInstance(idempotent.scene(), idempotent.type());
        try {
            instance.execute(joinPoint, idempotent);
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            instance.postProcessing();
            IdempotentContext.clean();
        }
    }

    private static Idempotent getIdempotent(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method declaredMethod = signature.getMethod();
        return declaredMethod.getAnnotation(Idempotent.class);

    }
}
