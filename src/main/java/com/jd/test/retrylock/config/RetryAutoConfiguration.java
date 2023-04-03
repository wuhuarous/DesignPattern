package com.jd.test.retrylock.config;


import com.jd.test.retrylock.annonation.RetryLock;
import com.jd.test.retrylock.core.RetryLockAspect;
import com.jd.test.retrylock.param.RetryLockParamService;
import com.jd.test.retrylock.param.RetryLockPramExecuteHandler;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jd
 * @date 2023/3/22 11:26
 */
@Configuration
public class RetryAutoConfiguration {


    @Bean
    public RetryLockParamService retryLockParamService(RedissonClient redissonClient) {
        return new RetryLockPramExecuteHandler(redissonClient);
    }

    @Bean
    public RetryLockAspect retryLockAspect() {
        return new RetryLockAspect();
    }

}
