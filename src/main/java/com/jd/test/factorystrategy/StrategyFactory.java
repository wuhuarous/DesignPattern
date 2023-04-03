package com.jd.test.factorystrategy;


/**
 * @author jd
 * @date 2022/8/4 15:04
 */
public abstract class StrategyFactory<T> {

    abstract RewardStrategy createRewardStrategy(Class<T> c) throws ClassNotFoundException, InstantiationException, IllegalAccessException;

}
