package com.jd.test.disruptor;


import com.lmax.disruptor.EventFactory;

/**
 * @author jd
 * @date 2023/8/7 14:44
 */
public class MessageEventFactory implements EventFactory<MessageEvent> {


    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}
