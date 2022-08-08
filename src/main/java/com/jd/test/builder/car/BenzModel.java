package com.jd.test.builder.car;


/**
 * @author jd
 * @date 2022/8/8 11:18
 */
public class BenzModel extends CarModel{
    @Override
    protected void start() {
        System.out.println("start");
    }

    @Override
    protected void stop() {
        System.out.println("stop");

    }

    @Override
    protected void alarm() {
        System.out.println("alarm");

    }

    @Override
    protected void engineBoom() {
        System.out.println("engineBoom");

    }
}
