package com.jd.test.ansync;


/**
 * @author jd
 * @date 2022/11/16 16:00
 */
public interface AnsyncResult<T> {


    Boolean isComplated();

    T getValue();

    void await();


}
