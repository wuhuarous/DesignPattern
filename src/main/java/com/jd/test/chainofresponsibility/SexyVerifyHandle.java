package com.jd.test.chainofresponsibility;


import java.util.List;

/**
 * 敏感校验
 * @author jd
 * @date 2023/3/10 15:41
 */
public class SexyVerifyHandle implements ExcelVerifyHandle<BaseDTO> {
    @Override
    public <T> List<BaseDTO> varifyHandle(T data) {

        return null;
    }
}
