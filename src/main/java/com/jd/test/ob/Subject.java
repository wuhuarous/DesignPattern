package com.jd.test.ob;


/**
 * @author jd
 * @date 2022/8/2 15:54
 */
public interface Subject {

    void register(Observer observer);

    void remove(Observer observer);

    void notify(String message);

}
