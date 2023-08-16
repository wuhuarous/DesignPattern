package com.jd.test.elastic.entity;


import com.alicloud.openservices.tablestore.timestream.model.annotation.Field;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author jd
 * @date 2022/8/25 10:56
 */
@Data
public class InfoEs implements Serializable {

    @Id
    private int id;

    private String name;

    private String age;


}
