package com.jd.test.idempotent.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jd
 * @date 2023/3/20 16:56
 */
@Data
@ConfigurationProperties(prefix = IdempotentProperties.PREFIX)
public class IdempotentProperties {

    public static final String PREFIX = "";


    private String prefix;


    private Long timeout;
}
