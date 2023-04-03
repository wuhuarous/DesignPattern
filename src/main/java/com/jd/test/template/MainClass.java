package com.jd.test.template;


import java.util.List;

/**
 * @author jd
 * @date 2022/11/16 17:44
 */
public class MainClass<T, U, R> extends ExcelImportTemplate {

    private List<T> parseData;
    String url;

    public MainClass(String url) {
        this.url = url;
    }

    @Override
    void parser(String url) {

    }

    @Override
    void vrify() {

    }

    @Override
    void vrifyData() {

    }

    @Override
    <R> void setCache(R R) {

    }


}
