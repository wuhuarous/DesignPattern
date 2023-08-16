package com.jd.test.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorDemo {

    public static class Producer {
        private final RingBuffer<MessageEvent> ringBuffer;

        public Producer(RingBuffer<MessageEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void onData(String data) {
            long sequence = ringBuffer.next();
            try {
                MessageEvent order = ringBuffer.get(sequence);
                order.setData(data);
            } finally {
                ringBuffer.publish(sequence);
            }
        }
    }

    public static class Consumer implements EventHandler<Order>{
        @Override
        public void onEvent(Order order, long l, boolean b) throws Exception {
            // 消费事件
            System.out.println("消费："+order.getData());
        }
    }

    public static class Order {
        private String data;

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        // 创建Disruptor
//        Disruptor<Order> disruptor = new Disruptor<>(Order::new, 16, DaemonThreadFactory.INSTANCE,
//                ProducerType.MULTI, new BlockingWaitStrategy());
//
//        // 设置EventHandler
//        disruptor.handleEventsWith((a,b,c)-> System.out.println("消费者："+a.getData()));
//
//        // 启动Disruptor
//        disruptor.start();

//        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();
//        Producer producer = new Producer(ringBuffer);
//
//        // 生产事件
//        for (int i = 0; i < 100; i++) {
//            producer.onData("Msg " + i);
//        }



        int bufferSize = 16;
        RingBuffer<MessageEvent>  disruptorBuffer = RingBuffer.createMultiProducer(new MessageEventFactory(),bufferSize);

        EventHandler<MessageEvent> stage = (messageEvent, l, b) -> {

            System.out.println(messageEvent.getData()+ l+ b);
        };

        SequenceBarrier  sequenceBarrier = disruptorBuffer.newBarrier();

        BatchEventProcessor<MessageEvent> stageProcessor = new BatchEventProcessor<>(disruptorBuffer,sequenceBarrier,stage);

//        stageProcessor.setExceptionHandler(new RuntimeException(""));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(stageProcessor);


        Producer producer = new Producer(disruptorBuffer);
        producer.onData("hello");
        producer.onData("word");
        executorService.shutdown();



    }
}