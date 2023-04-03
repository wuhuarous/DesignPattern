package com.jd.test.retrylock.core;


import com.jd.test.retrylock.annonation.RetryLock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jd
 * @date 2023/3/22 11:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RetryLockBuildWrapper {

    private RetryLock retryLock;

    private ProceedingJoinPoint joinPoint;

    private  String lockKey;

}
