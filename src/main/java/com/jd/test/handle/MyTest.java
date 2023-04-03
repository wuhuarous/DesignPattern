package com.jd.test.handle;


/**
 * @author jd
 * @date 2023/3/28 11:34
 */
public class MyTest {


//    public static void test(MyInterface myInterface){
//        myInterface.doSomething();
//    }
    public static void test1(MyInterface myInterface){
        myInterface.doSomething("name sss");
    }

    public static void main(String[] args) {

        test1(MyTest::test);


    }

    private static void test(String name) {

        System.out.println(name);
    }


}
