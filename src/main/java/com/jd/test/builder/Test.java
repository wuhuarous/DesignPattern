package com.jd.test.builder;


/**
 * @author jd
 * @date 2022/8/8 10:55
 */
public class Test {
    public static void main(String[] args) {
        DireCtor direCtor = new DireCtor();
        Product product = direCtor.getProduct();
        product.doSomething();
    }
}
