package com.jd.test.idempotent.core;


import com.jd.test.idempotent.annotation.Idempotent;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jd
 * @date 2023/3/20 17:09
 */
public abstract class AbstractIdempotentTemplate implements  IdempotentExecuteHandler {



    protected  abstract  IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint);


    public void execute(ProceedingJoinPoint joinPoint, Idempotent idempotent){
        IdempotentParamWrapper idempotentParamWrapper = buildWrapper(joinPoint).setIdempotent(idempotent);
        handler(idempotentParamWrapper);
    }


}
