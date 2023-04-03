package com.jd.test.state;


/**
 * @author jd
 * @date 2022/8/4 15:36
 */
public class Context {

    public final static ConcreteStateA con = new ConcreteStateA();

    public final static ConcreteStateB conB = new ConcreteStateB();

    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    public void handle1() {
        this.currentState.handle1();
    }

    public void handle2() {
        this.currentState.handle2();
    }

}
