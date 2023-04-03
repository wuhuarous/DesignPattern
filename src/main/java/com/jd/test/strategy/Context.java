package com.jd.test.strategy;


/**
 * @author jd
 * @date 2022/8/4 14:59
 */
public class Context {

    public Strategy strategy = null;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy() {
        strategy.strategy();
    }
}
