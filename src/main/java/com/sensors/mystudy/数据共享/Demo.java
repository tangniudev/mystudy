package com.sensors.mystudy.数据共享;

import lombok.extern.slf4j.Slf4j;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 * 一段代码块内如果存在对共享资源的多线程读写操作，称这段代码块为临界区
 * 虽然 java 中互斥和同步都可以采用 synchronized 关键字来完成，但它们还是有区别的：
 * 互斥是保证临界区的竞态条件发生，同一时刻只能有一个线程执行临界区代码
 * 同步是由于线程执行的先后、顺序不同、需要一个线程等待其它线程运行到某个点
 */
@Slf4j
public class Demo {
    private static int count = 0;
    static final Object room = new Object();

    public static void main(String[] args) throws InterruptedException {
        method2();


    }

    private static void method() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                count++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                count--;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", count);
    }

    private static void method2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (room) {
                    count++;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (room) {
                    count--;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", count);
    }
}
