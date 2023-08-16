package com.jd.test.elastic;


import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jd
 * @date 2022/8/2 11:50
 */
@SpringBootApplication
@EsMapperScan("com.jd.test.elastic.mapper")
public class EsApp {

    public static void main(String[] args) {

        SpringApplication.run(EsApp.class, args);
    }

}
