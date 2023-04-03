package com.jd.test.factorystrategy;


/**
 * 工厂策略模式
 *
 * @author jd
 * @date 2022/8/4 15:05
 */
public class FactorRewardStrategyFactory extends StrategyFactory {
    @Override
    RewardStrategy createRewardStrategy(Class c) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        RewardStrategy product = (RewardStrategy) Class.forName(c.getName()).newInstance();
        return product;
    }
}
