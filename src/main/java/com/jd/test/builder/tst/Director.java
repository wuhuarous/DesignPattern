package com.jd.test.builder.tst;


/**
 * @author jd
 * @date 2022/8/8 11:42
 */
public class Director {

    private Builder builder = new ConcreBuilder();

    public Product getProduct() {
        builder.set1();
        return this.builder.getProduct();
    }
}
