package com.jd.test.factorystrategy;


/**
 * @author jd
 * @date 2022/8/4 15:12
 */
public class InviteRewardImpl {

    public static void sendReward() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        FactorRewardStrategyFactory factorRewardStrategyFactory = new FactorRewardStrategyFactory();
        RewardStrategy rewardStrategy = factorRewardStrategyFactory.createRewardStrategy(NewUserRewardStrategyA.class);

        RewardContext rewardContext = new RewardContext(rewardStrategy);
        rewardContext.doStrategy(1);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        sendReward();
    }
}
