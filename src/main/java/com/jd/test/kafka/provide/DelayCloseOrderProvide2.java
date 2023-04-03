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

package com.jd.test.kafka.provide;

import com.alibaba.fastjson.JSON;
import com.jd.test.kafka.DelayCloseOrderEvent;
import com.jd.test.kafka.MessageWrapper;
import com.jd.test.kafka.MySource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 延迟关闭订单生产者
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 */
@Slf4j
@Component
@EnableBinding(MySource.class)
@AllArgsConstructor
public class DelayCloseOrderProvide2 {

    private final MessageChannel output2;
    /**
     * 延迟发送订单关闭消息
     *
     * @param delayCloseOrderEvent 关闭事件实体
     */
    public void delayCloseOrderSend(DelayCloseOrderEvent delayCloseOrderEvent) {
        String keys = UUID.randomUUID().toString();
        Message<?> message = MessageBuilder
                .withPayload(new MessageWrapper(keys, delayCloseOrderEvent))
                .build();
        long startTime = System.currentTimeMillis();
        boolean sendResult = false;
        try {
            sendResult = output2.send(message, 2000L);
        } finally {
            log.info("延迟关闭订单消息发送，发送状态: {}, Keys: {}, 执行时间: {} ms, 消息内容: {}", sendResult, keys, System.currentTimeMillis() - startTime, JSON.toJSONString(delayCloseOrderEvent));
        }
    }
}
