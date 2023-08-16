package com.jd.test.lamda;


/**
 * @author jd
 * @date 2023/7/26 15:19
 */
public class Test {
   private static Task  task;

    public Test(Task task) {

        this.task = task;
    }




    public interface  Task {

        public void flush(String e,String b);
    }


    public static void main(String[] args){
        Test  tesk = new Test((k,v)->{

            System.out.println("sdfd "+k+"=="+v);

        });

        System.out.println(1);
        task.flush("value","key");
    }

}
