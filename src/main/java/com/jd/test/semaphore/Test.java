package com.jd.test.semaphore;


import java.util.concurrent.Semaphore;

/**
 * @author jd
 * @date 2023/4/18 10:27
 */
public class Test {

    public static void main(String[] args) {

        for (int i = 0; i <= 10; i++) {

            int finalI = i;
            new Thread(() -> {
             tets(finalI);

         }).start();
        }


    }
  private final static Semaphore semaphore = new Semaphore(1);

    public static void tets(int i) {

        try {
            semaphore.acquire();

            Thread.sleep(1000l);
            System.out.println(Thread.currentThread().getId()+ "-"+i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
