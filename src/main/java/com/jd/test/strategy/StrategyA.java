package com.jd.test.strategy;


/**
 * @author jd
 * @date 2022/8/4 14:58
 */
public class StrategyA implements Strategy {
    @Override
    public void strategy() {
        System.out.println("正在执行A策略");
    }
}
