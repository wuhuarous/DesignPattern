package com.jd.test.builder.tst;

/**
 * @author jd
 * @date 2022/8/8 11:38
 */
public class ConcreBuilder extends Builder {
    /**
     * dfd
     */
    private Product product = new Product();

    @Override
    public void set1() {
        System.out.println("111");
    }

    @Override
    public Product getProduct() {
        return this.product;
    }
}
