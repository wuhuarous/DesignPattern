package com.jd.test.idempotent.core;


import com.jd.test.idempotent.annotation.Idempotent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jd
 * @date 2023/3/20 17:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class IdempotentParamWrapper {

    private Idempotent idempotent;


    private ProceedingJoinPoint joinPoint;


    private String lockKey;


}
