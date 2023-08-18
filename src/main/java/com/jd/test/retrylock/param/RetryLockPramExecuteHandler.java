package com.jd.test.retrylock.param;


import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.jd.test.idempotent.core.IdempotentContext;
import com.jd.test.retrylock.annonation.RetryLock;
import com.jd.test.retrylock.core.AbstractRetryLockTemplate;
import com.jd.test.retrylock.core.RetryLockBuildWrapper;
import com.jd.test.retrylock.core.RetryLockExcuteHandler;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jd
 * @date 2023/3/22 11:27
 */
@RequiredArgsConstructor
public class RetryLockPramExecuteHandler extends AbstractRetryLockTemplate implements RetryLockParamService {

    private final RedissonClient redissonClient;

    private final static String LOCK = "lock:param:restApi";

    @Override
    public RetryLockBuildWrapper buildWrapper(RetryLock retryLock, ProceedingJoinPoint joinPoint) {
        String lockKey = String.format("retryLock:path:%s:customerUserId:%s:param:%s", getServletPath(), 1, calcArgsMD5(joinPoint));
        return RetryLockBuildWrapper.builder().retryLock(retryLock).joinPoint(joinPoint).lockKey(lockKey).build();
    }

    private String calcArgsMD5(ProceedingJoinPoint joinPoint) {
        String md5 = DigestUtil.md5Hex(JSON.toJSONBytes(joinPoint.getArgs()));
        return md5;
    }

    private String getServletPath() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest().getServletPath();
    }

    @Override
    public void handler(RetryLockBuildWrapper buildWrapper) {
        String lockKey = buildWrapper.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        ReentrantLock lock1 = new ReentrantLock();

        lock1.tryLock();

        if (!lock.tryLock()) {
            throw new RuntimeException(buildWrapper.getRetryLock().message());
        }
        IdempotentContext.put(LOCK, lock);
    }

    @Override
    public void postProcessing() {
        RLock lock = null;
        try {
            lock = (RLock) IdempotentContext.getKey(LOCK);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }

    }
}
