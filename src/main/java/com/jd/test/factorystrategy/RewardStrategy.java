package com.jd.test.factorystrategy;


/**
 * @author jd
 * @date 2022/8/4 15:01
 */
public abstract class RewardStrategy {

    /**
     * 奖励
     * @param userId
     */
    public abstract  void reward(long userId);

    /**
     * 更新用户信息以及结算
     * @param userId
     * @param reward
     */
    public void insertRewardAndSet(long userId,int reward){};
}
