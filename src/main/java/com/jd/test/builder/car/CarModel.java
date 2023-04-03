package com.jd.test.builder.car;


import java.util.ArrayList;

/**
 * @author jd
 * @date 2022/8/8 11:12
 */
public abstract class CarModel {

    private ArrayList<String> seq = new ArrayList<>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    final public void run() {
        for (int i = 0; i < this.seq.size(); i++) {
            String actionName = this.seq.get(i);
            if (actionName.equalsIgnoreCase("start")) {
                this.start(); //启动汽车
            } else if (actionName.equalsIgnoreCase("stop")) {
                this.stop(); //停止汽车
            } else if (actionName.equalsIgnoreCase("alarm")) {
                this.alarm(); //喇叭开始叫了
            } else if (actionName.equalsIgnoreCase("engine boom")) {
                //如果是engine boom关键this.engineBoom(); //引擎开始轰鸣
            }
        }


    }

    //把传递过来的值传递到类内
    final public void setSequence(ArrayList seq) {
        this.seq = seq;
    }
}
