package com.jd.test.strategy;


/**
 * @author jd
 * @date 2023/5/22 16:59
 */
public interface AbstarctExecuteStrategy <REQUEST,RESPONSE>{


    String mark();

    default void execute(REQUEST  requestParam){

    }

    default RESPONSE executeResp(REQUEST requestParam){return null;}
}
