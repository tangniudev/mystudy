package com.sensors.mystudy.数据共享;

import lombok.extern.slf4j.Slf4j;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 * 面向对象改进
 * 把需要保护的共享变量放入一个类
 */
@Slf4j
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0 ; i < 500; i++) {
                room.increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0 ; i < 500; i++) {
                room.decrement();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", room.get());
    }
}
