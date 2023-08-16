package com.jd.test.elastic.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.IdType;

import java.io.Serializable;

/**
 * @author jd
 * @date 2022/8/25 10:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsCustomer implements Serializable {

    @IndexId(type = IdType.CUSTOMIZE)
    private Long id;

    @IndexField(searchAnalyzer = "ik_max_word",fieldType = FieldType.KEYWORD_TEXT)
    private String iphone;

    private Integer sex;

    @IndexField(fieldType = FieldType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private String createTime;

    private String data;




}
