package com.jd.test.factorystrategy;


/**
 * @author jd
 * @date 2022/8/4 15:06
 */
public class RewardContext {
    private RewardStrategy rewardStrategy;

    public RewardContext(RewardStrategy rewardStrategy) {
        this.rewardStrategy = rewardStrategy;
    }

    public void doStrategy(int userId) {
        rewardStrategy.reward(userId);
        //do something
    }
}
