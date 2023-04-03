package com.jd.test.elastic;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

/**
 * @author jd
 * @date 2022/8/25 11:05
 */
public interface UserEsRepository extends ElasticsearchRepository<UserEs, Long> {
}
