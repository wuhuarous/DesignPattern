package com.jd.test.template;


import java.util.List;

/**
 * @author jd
 * @date 2022/11/16 17:40
 */
public abstract class ExcelImportTemplate {


    abstract void parser(String url);

    abstract void vrify();

    abstract void vrifyData();

    abstract <R> void setCache(R R);


}
