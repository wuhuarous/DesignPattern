/*
package com.jd.test.hippo4j.test;


import cn.hippo4j.springboot.starter.core.ThreadPoolSubscribeCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author jd
 * @date 2023/2/27 17:30
 *//*

@Component
public class TestClass {
    @Autowired
    private ThreadPoolDynamicRefresh1 threadPoolDynamicRefresh1;

    public void test(String context) {
        test(context, threadPoolDynamicRefresh1::dynamicRefresh1);
    }

    public Map<String, TestInter> map = new HashMap<>(1);


    public void test(String context, ThreadPoolSubscribeCallback threadPoolSubscribeCallback) {
        TestInter testInter = new TestInter() {
            @Override
            public void myInter(String context) {
                System.out.println(context);
                threadPoolSubscribeCallback.callback(context);
            }

        };

        map.put("1", testInter);
    }


}
*/
