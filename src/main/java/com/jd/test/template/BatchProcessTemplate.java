package com.jd.test.template;


/**
 * @author jd
 * @date 2023/7/27 10:20
 */
public abstract class BatchProcessTemplate implements DataProcessor {

    DataProcessor data;
    final void templateMethod(){
        loadData();
        parocessData();
        saveResult();
    }

    abstract void loadData();

    abstract void saveResult();

    void setDataProcess(DataProcessor  data){
        this.data = data;
    }
}
