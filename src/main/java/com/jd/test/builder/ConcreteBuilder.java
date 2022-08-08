package com.jd.test.builder;


/**
 * @author jd
 * @date 2022/8/8 10:50
 */
public class ConcreteBuilder extends Builder {

    public Product product = new Product();

    @Override
    public void setPrat() {
        /**
         *
         */
        System.out.println("我们设置了prat");
        System.out.println("我们设置了prat1");

    }

    @Override
    public Product buildProduct() {
        return product;
    }
}
