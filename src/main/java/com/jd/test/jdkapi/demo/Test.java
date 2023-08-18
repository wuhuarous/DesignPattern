package com.jd.test.jdkapi.demo;


import org.apache.commons.compress.utils.Lists;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jd
 * @date 2023/8/17 11:11
 */
public class Test {

    public static void main1(String[] args) {

        Person person = new Person();

//        TestInterface o =(TestInterface) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new TestInvocationHandler());
//        o.exception();


    }
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        List<String> strList = Lists.newArrayList();

        int practicalSize = 1000000;

        for (int i = 0; i < practicalSize; i++) {
            strList.add(String.valueOf(i));
        }

        strList.parallelStream().forEach(each -> integerList.add(Integer.parseInt(each)));

        System.out.println("  >>> integerList 预计长度 :: {}"+ practicalSize);
        System.out.println("  >>> integerList 实际长度 :: {}"+integerList.size());
    }

}
