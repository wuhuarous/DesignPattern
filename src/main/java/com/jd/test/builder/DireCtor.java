package com.jd.test.builder;


/**
 * @author jd
 * @date 2022/8/8 10:52
 */
public class DireCtor {
    private Builder builder = new ConcreteBuilder();

    public Product getProduct() {

        builder.setPrat();
        return builder.buildProduct();
    }
}
