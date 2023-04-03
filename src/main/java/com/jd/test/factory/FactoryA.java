package com.jd.test.factory;


/**
 * @author jd
 * @date 2022/8/4 14:56
 */
public class FactoryA extends Factory {
    @Override
    Product createProduct(Class c) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Product product = (Product) Class.forName(c.getName()).newInstance();
        return product;
    }
}
