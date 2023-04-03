package com.jd.test.chainofresponsibility;


import java.util.List;

/**
 * 非空校验
 * @author jd
 * @date 2023/3/10 15:41
 */
public class NotNullVerifyHandle implements ExcelVerifyHandle<MessageError> {
    @Override
    public  <T> List<MessageError> varifyHandle(T data) {
        return null;
    }
}
