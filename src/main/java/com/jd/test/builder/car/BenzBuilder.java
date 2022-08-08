package com.jd.test.builder.car;


import java.util.ArrayList;

/**
 * @author jd
 * @date 2022/8/8 11:17
 */
public class BenzBuilder extends CarBuilder{
    private BenzModel benzModel = new BenzModel();
    @Override
    public void setSeq(ArrayList<String> list) {
        this.benzModel.setSequence(list);
    }

    @Override
    public CarModel getCarModel() {
        return this.benzModel;
    }
}
