package com.jd.test.kafka;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义Sink
 * @author jd
 * @date 2023/3/17 17:18
 */
public interface MySink extends Sink {

    String INPUT2 = "input2";

    @Input("input2")
    SubscribableChannel input2();
}
