package com.jd.test.idempotent.core.param;


import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.jd.test.idempotent.core.AbstractIdempotentTemplate;
import com.jd.test.idempotent.core.IdempotentContext;
import com.jd.test.idempotent.core.IdempotentParamWrapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *   先定义枚举 TYPE 通过参数 token 或者spel
 *   在定义注解@Idempotent 包括 key  message  type  scene prefix timeout
 *   在定义一个handler接口  处理（用参数做key加锁） 执行（调用handler方法）  后置操作（释放锁）
 *
 *
 *
 *
 * @author jd
 * @date 2023/3/20 17:15
 */
@RequiredArgsConstructor
public class IdempotentParamExcuteHandler extends AbstractIdempotentTemplate implements IdempotentParamService {

    private final RedissonClient redissonClient;

    private final static String LOCK = "lock:param:restAPI";
    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {

        String lockKey = String.format("idempotent:path:%s:currentUserId:md5:%s",getServletPath(),getCurrentUserId(),calcArgsMD5(joinPoint));
        return IdempotentParamWrapper.builder().lockKey(lockKey).build();
    }


    /**
     * @return 当前操作用户 ID
     */
    private String getCurrentUserId() {
        return null;
    }


    /**
     * @return joinPoint md5
     */
    private String calcArgsMD5(ProceedingJoinPoint joinPoint) {
        String md5 = DigestUtil.md5Hex(JSON.toJSONBytes(joinPoint.getArgs()));
        return md5;
    }

    private String getServletPath() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest().getServletPath();
    }
    @Override
    public void handler(IdempotentParamWrapper wrapper) {

        String lockKey = wrapper.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if(!lock.tryLock()){
            throw new RuntimeException(wrapper.getIdempotent().message());
        }
        IdempotentContext.put(LOCK,lock);

    }

    @Override
    public void postProcessing() {
        RLock lock = null;
        try {
            lock = (RLock) IdempotentContext.getKey(LOCK);
        }finally {
            if(lock != null){
                lock.unlock();
            }
        }

    }
}
