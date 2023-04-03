package com.jd.test.builder.car;


import java.util.ArrayList;

/**
 * @author jd
 * @date 2022/8/8 11:11
 */
public abstract class CarBuilder {
    public abstract void setSeq(ArrayList<String> list);

    public abstract CarModel getCarModel();
}
