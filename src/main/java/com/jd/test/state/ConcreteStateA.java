package com.jd.test.state;


/**
 * @author jd
 * @date 2022/8/4 15:37
 */
public class ConcreteStateA extends State{
    @Override
    public void handle1() {

    }

    @Override
    public void handle2() {
        super.context.setCurrentState(Context.conB);
        super.context.handle2();
    }
}
