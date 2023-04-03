package com.jd.test.hippo4j.test;


import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author jd
 * @date 2023/2/27 17:35
 */
@Service
public class ThreadPoolDynamicRefreshImpl implements ThreadPoolDynamicRefresh1 {

    @Override
    public void dynamicRefresh(String content) {
        System.out.println(content);
    }


    @Override
    public void dynamicRefresh1(String content) {

        System.out.println("dynamicRefresh1");
    }

}
