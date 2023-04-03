package com.jd.test.idempotent.annotation;


import com.jd.test.idempotent.enums.IdempotentSceneEnum;
import com.jd.test.idempotent.enums.IdempotentTypeEnum;

import java.lang.annotation.*;

/**
 * @author jd
 * @date 2023/3/20 16:26
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {


    String key() default "";


    String message() default "您操作太快，请稍后再试";


    IdempotentTypeEnum type() default IdempotentTypeEnum.PARAM;


    IdempotentSceneEnum scene() default IdempotentSceneEnum.RESTAPI;


    String uniqueKeyPrefix() default "";

    long keyTimeout() default 3600L;
}
