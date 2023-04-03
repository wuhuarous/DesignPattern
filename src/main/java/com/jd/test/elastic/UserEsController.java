/*
package com.jd.test.elastic;


import org.apache.lucene.queryparser.xml.QueryBuilder;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.awt.*;
import java.util.Optional;
import java.util.Queue;

*/
/**
 * @author jd
 * @date 2022/8/25 11:06
 *//*

@RestController
public class UserEsController {

    @Resource
    private UserEsRepository userEsRepository;

    @PostConstruct
    private void test(){
        UserEs es = new UserEs();

        for (int i = 0; i < 20; i++) {
            es.setId(1301+i);
            es.setName("李四"+i);
            es.setSex("女");
            InfoEs infoEs = new InfoEs();
            infoEs.setAge("16");
            infoEs.setId(i);
            infoEs.setName("1"+i);
            es.setInfo(infoEs);
            userEsRepository.save(es);
        }

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        queryBuilder.must(QueryBuilders.matchQuery("name","张三"));

        builder.withQuery(queryBuilder);
        builder.withPageable(PageRequest.of(1,10));
        System.out.println("-------------");
//        Page<UserEs> search = userEsRepository.searchSimilar(es,null,PageRequest.of(1,10));
//        System.out.println(search);
//        userEsRepository.fina();
    }

}
*/
