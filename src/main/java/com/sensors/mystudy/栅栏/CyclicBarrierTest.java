package com.sensors.mystudy.栅栏;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 7; i++) {//三个一循环
            pool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    barrier.await();
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"aaa");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
