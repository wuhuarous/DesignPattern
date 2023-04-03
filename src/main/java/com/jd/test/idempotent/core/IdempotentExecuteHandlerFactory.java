package com.jd.test.idempotent.core;


import com.jd.test.idempotent.ApplicationContextHolder;
import com.jd.test.idempotent.core.param.IdempotentParamService;
import com.jd.test.idempotent.enums.IdempotentSceneEnum;
import com.jd.test.idempotent.enums.IdempotentTypeEnum;

/**
 * @author jd
 * @date 2023/3/21 11:15
 */
public class IdempotentExecuteHandlerFactory {


    public static IdempotentExecuteHandler getInstance(IdempotentSceneEnum scene, IdempotentTypeEnum type) {

        IdempotentExecuteHandler result = null;
        switch (scene) {

            case RESTAPI:
                switch (type) {
                    case PARAM:
                        result = ApplicationContextHolder.getBean(IdempotentParamService.class);
                        break;
                    case TOKEN:
                        result = null;
                        break;
                    default:
                        break;
                }
            case MQ:
                break;
            default:
                break;
        }

        return result;
    }
}
