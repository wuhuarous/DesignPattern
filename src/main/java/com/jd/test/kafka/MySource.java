package com.jd.test.kafka;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义发送方
 * @author jd
 * @date 2023/3/17 17:20
 */
public interface MySource extends Source {

    String OUTPUT2 = "output2";

    @Output("output2")
    MessageChannel output2();

}
