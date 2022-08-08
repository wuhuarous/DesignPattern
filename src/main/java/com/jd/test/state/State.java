package com.jd.test.state;


/**
 * @author jd
 * @date 2022/8/4 15:36
 */
public abstract class State {
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void handle1();

    public abstract  void handle2();


}
