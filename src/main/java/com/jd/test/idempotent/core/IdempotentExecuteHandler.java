package com.jd.test.idempotent.core;


import com.jd.test.idempotent.annotation.Idempotent;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jd
 * @date 2023/3/20 17:03
 */
public interface IdempotentExecuteHandler {


    void handler(IdempotentParamWrapper wrapper);


    void execute(ProceedingJoinPoint joinPoint, Idempotent idempotent);

    default void postProcessing() {


    }
}
