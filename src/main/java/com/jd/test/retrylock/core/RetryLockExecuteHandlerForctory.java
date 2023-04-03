package com.jd.test.retrylock.core;


import com.jd.test.idempotent.ApplicationContextHolder;
import com.jd.test.retrylock.enums.RetryLockSceneEnums;
import com.jd.test.retrylock.enums.RetryLockTypeEnums;
import com.jd.test.retrylock.param.RetryLockPramExecuteHandler;

/**
 * @author jd
 * @date 2023/3/22 11:45
 */
public class RetryLockExecuteHandlerForctory {

    public static RetryLockExcuteHandler getInstance(RetryLockSceneEnums scene, RetryLockTypeEnums type){

        RetryLockExcuteHandler  result = null;

        switch (scene){
            case RESTAPI:
                switch (type){
                    case PARAM:
                        result = ApplicationContextHolder.getBean(RetryLockPramExecuteHandler.class);
                        break;
                }
                break;

        }
        return result;
    }
}
