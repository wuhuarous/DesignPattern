package com.jd.test.builder.car;


import java.util.ArrayList;

/**
 * @author jd
 * @date 2022/8/8 11:21
 */
public class Director {

    private ArrayList<String> seq = new ArrayList<>();

    private BenzBuilder benzBuilder = new BenzBuilder();

    public BenzModel getABenzModel(){
        this.seq.clear();
        this.seq.add("start");
        this.seq.add("stop");
        this.benzBuilder.setSeq(this.seq);
        return (BenzModel) this.benzBuilder.getCarModel();

    }
}
