package com.jd.test.factory;


/**
 * 工厂模式
 *
 * @author jd
 * @date 2022/8/4 14:55
 */
public abstract class Factory<T> {

    abstract Product createProduct(Class<T> c) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
}
