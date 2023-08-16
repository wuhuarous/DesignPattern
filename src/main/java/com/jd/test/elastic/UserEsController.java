
package com.jd.test.elastic;


import cn.hutool.core.lang.generator.UUIDGenerator;
import com.jd.test.elastic.entity.EsCustomer;
import com.jd.test.elastic.entity.EsUser;
import com.jd.test.elastic.mapper.UserMapper;
import com.jd.test.elastic.mapper.UserTMapper;
import lombok.RequiredArgsConstructor;
import org.dromara.easyes.core.biz.EsPageInfo;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.core.EsWrappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * @author jd
 * @date 2022/8/25 11:06
 */

@RestController
@RequiredArgsConstructor
public class UserEsController {

    private final UserMapper userMapper;


    @PostMapping("/page")
    private EsPageInfo<EsUser> test(@RequestBody List<Object> nextSearchAfter) throws IOException {

        //wrappers两种构建方式
        //第一种 直接new 例如：查询  new LambdaEsQueryWrapper<>() 更新： new LambdaEsUpdateWrapper<>()
        LambdaEsQueryWrapper<EsUser> wrapper = new LambdaEsQueryWrapper<>();
//        wrapper.le(EsUser::getAge, 40);
        wrapper.eq(EsUser::getName, "黄鑫");
        wrapper.orderByDesc(EsUser::getId);

        EsPageInfo<EsUser> esUserEsPageInfo = userMapper.pageQuery(wrapper, 1, 10);



        //第二种通过EsWrappers.lambdaQuery()创建
        EsPageInfo<EsUser> list = EsWrappers.lambdaChainQuery(userMapper).page(1, 10);


        return esUserEsPageInfo;
    }

    @PostMapping("/getUserList")
    private List<EsUser> getUserList() throws IOException {
        List<EsUser> es = EsWrappers.lambdaChainQuery(userMapper).orderByDesc(EsUser::getId).list();
        return es;
    }


    @PostMapping("/add")
    private Integer add(@RequestBody EsUser esUser) throws IOException {
        return userMapper.insert(esUser);
    }

}

