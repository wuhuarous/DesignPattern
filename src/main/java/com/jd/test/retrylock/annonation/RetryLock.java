package com.jd.test.retrylock.annonation;


import com.jd.test.retrylock.enums.RetryLockSceneEnums;
import com.jd.test.retrylock.enums.RetryLockTypeEnums;

import java.lang.annotation.*;

/**
 * @author jd
 * @date 2023/3/22 11:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface RetryLock {

    String key() default "";

    String message() default "您的请求太快，请稍后重试";

    RetryLockTypeEnums type() default RetryLockTypeEnums.PARAM;

    RetryLockSceneEnums scene() default RetryLockSceneEnums.RESTAPI;

    String prefix() default "";

    long timeout() default 3600L;
}
