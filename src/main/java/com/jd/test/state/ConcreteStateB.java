package com.jd.test.state;


/**
 * @author jd
 * @date 2022/8/4 15:37
 */
public class ConcreteStateB extends State{

    @Override
    public void handle1() {
        super.context.setCurrentState(Context.con);
        super.context.handle1();
    }

    @Override
    public void handle2() {

    }
}
