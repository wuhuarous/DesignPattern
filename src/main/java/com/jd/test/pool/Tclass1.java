package com.jd.test.pool;


import java.util.concurrent.Callable;

/**
 * @author jd
 * @date 2022/8/3 15:40
 */
public class Tclass1 implements Callable<String> {

    private String name;

    public Tclass1(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {

        return name;
    }
}
