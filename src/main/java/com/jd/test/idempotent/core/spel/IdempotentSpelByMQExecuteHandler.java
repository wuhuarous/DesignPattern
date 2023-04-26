package com.jd.test.idempotent.core.spel;


import com.jd.test.cache.DistributedCache;
import com.jd.test.cache.StringRedisTemplateProxy;
import com.jd.test.idempotent.RepeatConsumptionException;
import com.jd.test.idempotent.annotation.Idempotent;
import com.jd.test.idempotent.core.AbstractIdempotentTemplate;
import com.jd.test.idempotent.core.IdempotentAspect;
import com.jd.test.idempotent.core.IdempotentContext;
import com.jd.test.idempotent.core.IdempotentParamWrapper;
import com.jd.test.idempotent.enums.IdempotentMQConsumeStatusEnum;
import com.jd.test.idempotent.toolkit.LogUtil;
import com.jd.test.idempotent.toolkit.SpELUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author jd
 * @date 2023/4/18 16:17
 */
@RequiredArgsConstructor
public class IdempotentSpelByMQExecuteHandler extends AbstractIdempotentTemplate implements IdempotentSpELService {

    private final DistributedCache distributedCache;

    private final static int TIMEOUT = 600;

    private final static String WRAPPER = "lock:spEL:MQ";

    @SneakyThrows
    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        Idempotent idempotent = IdempotentAspect.getIdempotent(joinPoint);
        String key = (String) SpELUtil.parseKey(idempotent.key(), ((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs());
        return IdempotentParamWrapper.builder().lockKey(key).joinPoint(joinPoint).build();
    }

    @Override
    public void handler(IdempotentParamWrapper wrapper) {
        String uniqueKey = wrapper.getIdempotent().uniqueKeyPrefix() + wrapper.getLockKey();
        Boolean aBoolean = ((StringRedisTemplate) distributedCache.getInstance()).opsForValue()
                .setIfAbsent(uniqueKey, IdempotentMQConsumeStatusEnum.CONSUMED.getCode(), TIMEOUT, TimeUnit.SECONDS);
        if (aBoolean != null && !aBoolean) {
            String s = distributedCache.get(uniqueKey, String.class);
            boolean error = IdempotentMQConsumeStatusEnum.isError(s);
            LogUtil.getLog(wrapper.getJoinPoint()).warn("[{}] MQ repeated consumption, {}.", uniqueKey, error ? "Wait for the client to delay consumption" : "");
            throw new RepeatConsumptionException(error);
        }
        IdempotentContext.put(WRAPPER, wrapper);
    }

    @Override
    public void postProcessing() {
        IdempotentParamWrapper idwrapper = (IdempotentParamWrapper) IdempotentContext.getKey(WRAPPER);
        if (idwrapper != null) {
            Idempotent idempotent = idwrapper.getIdempotent();
            String uniqueKey = idempotent.uniqueKeyPrefix() + idwrapper.getLockKey();
            distributedCache.put(uniqueKey, IdempotentMQConsumeStatusEnum.CONSUMED.getCode(), idempotent.keyTimeout(), TimeUnit.SECONDS);
        }
    }
}
