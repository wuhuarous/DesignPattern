package com.jd.test.elastic;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author jd
 * @date 2022/8/25 10:56
 */
@Document(indexName = "info")
@Data
public class InfoEs implements Serializable {

    @Id
    private int id;

    @Field
    private String name;

    @Field
    private String age;


}
