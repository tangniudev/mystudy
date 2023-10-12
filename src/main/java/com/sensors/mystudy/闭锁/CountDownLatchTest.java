package com.sensors.mystudy.闭锁;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
         CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 7; i++) {
            pool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                    countDownLatch.await();//没有等待到需要的数量会一直等待
                    System.out.println(Thread.currentThread().getName()+"aaa");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
