/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jd.test.kafka.aspect;

import com.alibaba.fastjson.JSON;
import com.jd.test.kafka.MessageWrapper;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Arrays;
import java.util.Optional;

/**
 * {@link StreamListener} 日志环绕打印
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 */
@Aspect
public final class StreamListenerLogPrintAspect {
    
    @SneakyThrows
    @Around("@within(org.springframework.cloud.stream.annotation.StreamListener) || @annotation(org.springframework.cloud.stream.annotation.StreamListener)")
    public Object streamListenerLogPrint(ProceedingJoinPoint joinPoint) {
        Object result;
        boolean executeResult = true;
        long startTime = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Logger log = LoggerFactory.getLogger(methodSignature.getDeclaringType());
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            executeResult = false;
            throw ex;
        } finally {
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                Optional<MessageWrapper> messageWrapperOptional = Arrays.stream(args)
                        .filter(each -> each instanceof MessageWrapper)
                        .map(each -> (MessageWrapper) each)
                        .findFirst();
                if (messageWrapperOptional.isPresent()) {
                    MessageWrapper messageWrapper = messageWrapperOptional.get();
                    log.info("Execute result: {}, Keys: {}, Dispatch time: {} ms, Execute time: {} ms, Message: {}",
                            executeResult,
                            messageWrapper.getKeys(),
                            System.currentTimeMillis() - messageWrapper.getTimestamp(),
                            System.currentTimeMillis() - startTime,
                            JSON.toJSONString(messageWrapper.getMessage()));
                }
            }
        }
        return result;
    }
}
