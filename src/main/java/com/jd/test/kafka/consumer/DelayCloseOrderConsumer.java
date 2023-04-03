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

package com.jd.test.kafka.consumer;

import com.jd.test.kafka.DelayCloseOrderEvent;
import com.jd.test.kafka.MessageWrapper;
import com.jd.test.kafka.MySink;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 延迟关闭订单消费者
 *
 * 为了保证偏移量的正确性和一致性，我们可以考虑以下几点：
 *
 * 保证消费者的幂等性：幂等性是指对于同一条消息的多次消费操作，最终的结果是一致的。为了保证消费者的幂等性，我们可以在消费消息之前，先检查消息是否已经被消费过。如果消息已经被消费过，那么就直接跳过，不再进行消费。这样就可以避免重复消费的问题。
 *
 * 合理设置提交偏移量的策略：提交偏移量时，应该考虑到消息消费的可靠性和性能。一般来说，手动提交偏移量比自动提交偏移量更加可靠，因为手动提交偏移量可以确保消费者在消费完消息后再提交偏移量，从而避免了提交偏移量失败的情况。但是，手动提交偏移量也会带来一定的性能损失。因此，在实际使用中，应该根据业务场景和消费者的需求，选择合适的提交偏移量策略。
 *
 * 处理提交偏移量失败的情况：提交偏移量时，可能会发生提交失败的情况，比如Kafka集群不可用、网络故障等。为了避免这种情况导致消息的重复消费或漏消费，我们可以在捕获提交偏移量失败的异常后，对该异常进行处理。处理的方式可以是重试提交偏移量，也可以是将消费者挂起等待，直到Kafka集群恢复正常。
 *
 * 使用事务机制：Kafka支持事务机制，可以在多个分区中的消息消费和偏移量提交之间保持一致性。使用事务机制可以确保消费者在消费消息和提交偏移量时，要么全部成功，要么全部失败。这样可以避免消息的重复消费和漏消费。
 *
 * 通过上述几点的实践和应用，可以有效地保证偏移量的正确性和一致性，从而提高消息的可靠性和一致性。
 *
 *
 * 为了避免 Kafka 消息丢失，可以采取以下措施：
 *
 * 设置正确的副本因子：在 Kafka 集群中，每个分区可以有多个副本，副本的数量由副本因子指定。当主副本出现故障时，Kafka 会自动选举一个新的副本作为主副本，以确保数据不会丢失。
 *
 * 开启 ack 确认机制：在消息发送时，可以配置 ack 确认机制来确保消息被成功发送到 Kafka 集群。ack 有三种模式，分别是 all、-1 和 1，其中 all 模式要求所有副本都确认消息才算发送成功，-1 模式要求主副本和至少一个副本确认消息，1 模式只要求主副本确认消息。使用 ack 确认机制可以有效地避免消息丢失问题。
 *
 * 设置适当的发送和接收缓存大小：在生产者和消费者端，可以设置发送和接收缓存大小，以确保消息能够得到正确的处理。
 *
 * 配置适当的重试机制：当消息发送或接收失败时，可以配置适当的重试机制，例如设置重试次数、重试间隔时间等，以确保消息能够最终被正确处理。
 *
 * 定期备份和恢复数据：为了避免数据丢失，可以定期备份 Kafka 数据，并在需要时进行数据恢复。同时，还可以配置数据复制机制，例如使用 MirrorMaker 工具将数据从一个 Kafka 集群复制到另一个 Kafka 集群。
 *
 * 总之，为了避免 Kafka 消息丢失问题，需要在多个方面采取措施，从副本设置、确认机制、缓存大小、重试机制等多个方面入手，以确保 Kafka 消息能够得到正确的处理
 *
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 */
@Slf4j
@Component
@AllArgsConstructor
@EnableBinding(MySink.class)
public class DelayCloseOrderConsumer {


    //condition 条件过滤
    @StreamListener(MySink.INPUT)
    public void delayCloseOrderConsumer(@Payload MessageWrapper<DelayCloseOrderEvent> messageWrapper, Acknowledgment ack) {
        String orderSn = messageWrapper.getMessage().getOrderSn();
        System.out.println(orderSn);
        //手动提交偏移量
        ack.acknowledge();

    }

    @StreamListener(MySink.INPUT2)
    public void delayCloseOrderConsumer2(@Payload MessageWrapper<DelayCloseOrderEvent> messageWrapper) {
        String orderSn = messageWrapper.getMessage().getOrderSn();
        System.out.println(orderSn);

    }

    /*@Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(MySink.INPUT);
        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        containerProperties.setTransactionManager(kafkaTransactionManager);
        containerProperties.setKafkaProperties(kafkaStreamsProperties.buildStreamsProperties());

        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(kafkaTransactionManager, containerProperties);
        container.setBeanName("kafkaMessageListenerContainer");
        container.setupMessageListener((AcknowledgingMessageListener<String, String>) (record, acknowledgment) -> {
            myBinding.input().send(record.value());
            acknowledgment.acknowledge();
        });

        return container;
    }*/
}
