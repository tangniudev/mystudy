package com.sensors.mystudy.创建线程;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/20
 * 打断 sleep 的线程, 会清空打断状态，以 sleep 为例
 * 打断 park 线程, 不会清空打断状态
 */
@Slf4j
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        method04();
    }

    private static void method01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }

    private static void method02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
        while (true){
            Thread thread = Thread.currentThread();
            boolean b = thread.isInterrupted();
            if (b){
                log.debug("打断状态： {}",b);
                break;
            }
        }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }

    private static void method03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }

    private static void method04() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
       TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }

}
