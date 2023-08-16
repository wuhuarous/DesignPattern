package com.jd.test.es;


import com.jd.test.elastic.EsApp;
import com.jd.test.elastic.mapper.UserMapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author jd
 * @date 2023/8/9 11:36
 */
@SpringBootTest(classes = EsApp.class)
public class EsTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testEs() {
//        userMapper.insert(new EsUser(1L, "name", 18, "haha"));
    }
}
