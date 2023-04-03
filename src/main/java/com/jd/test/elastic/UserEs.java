package com.jd.test.elastic;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author jd
 * @date 2022/8/25 10:56
 */
@Document(indexName = "user")
@Data
public class UserEs implements Serializable {

    @Id
    private int id;

    @Field
    private String name;

    @Field
    private String sex;

    @Field
    @JsonFormat
    private InfoEs info;
}
