package com.jd.test.pool;


import java.util.concurrent.Callable;

/**
 * @author jd
 * @date 2022/8/3 15:40
 */
public class Tclass implements Callable<Integer> {

    private Integer name;

    public Tclass(int name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {

        return name;
    }

    @Override
    public String toString() {
        return "Tclass{" +
                "name=" + name +
                '}';
    }
}
