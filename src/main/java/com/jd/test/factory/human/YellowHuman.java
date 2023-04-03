package com.jd.test.factory.human;


/**
 * @author jd
 * @date 2022/8/5 14:42
 */
public class YellowHuman implements Human {
    @Override
    public void getColer() {
        System.out.println("我们是高贵的种族");
    }

    @Override
    public void getName() {
        System.out.println("我叫五花");
    }
}
