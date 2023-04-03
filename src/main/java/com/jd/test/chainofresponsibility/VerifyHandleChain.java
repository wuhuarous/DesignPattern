/*
package com.jd.test.chainofresponsibility;


import cn.hippo4j.common.config.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

*/
/**
 * @author jd
 * @date 2023/3/10 15:45
 *//*

@AllArgsConstructor
@Component
public class VerifyHandleChain<T> implements InitializingBean {

    private final List<ExcelVerifyHandle> handles = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, ExcelVerifyHandle> beansOfType = ApplicationContextHolder.getBeansOfType(ExcelVerifyHandle.class);
        beansOfType.forEach((key, value) -> handles.add(value));

    }

    private void verify(T data) {
        for (ExcelVerifyHandle handle : handles) {
            handle.varifyHandle(data);
        }

    }
}
*/
