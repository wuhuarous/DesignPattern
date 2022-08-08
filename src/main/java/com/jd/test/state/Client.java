package com.jd.test.state;


/**
 * @author jd
 * @date 2022/8/4 16:16
 */
public class Client {


    public static void main(String[] args) {
        Context  context = new Context();
        context.setCurrentState(new ConcreteStateA());
        context.handle1();
        context.handle2();
    }
}
