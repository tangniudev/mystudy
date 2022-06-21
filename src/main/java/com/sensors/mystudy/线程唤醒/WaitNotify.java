package com.sensors.mystudy.线程唤醒;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 *
 */
@Slf4j
public class WaitNotify {
    final static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行1....");
                try {
                    obj.wait(); // 让线程在obj上一直等待下去,等待notify，wait方法会释放obj锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码1....");
            }
        }).start();
        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行2....");
                try {
                    obj.wait(); // 让线程在obj上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码2....");
            }
        }).start();
        // 主线程两秒后执行
        sleep(2000);
        log.debug("唤醒 obj 上其它线程");
        synchronized (obj) {
            obj.notify(); // 唤醒obj上一个线程 唤醒的线程有顺序
//             obj.notifyAll(); // 唤醒obj上所有等待线程
        }
    }
}
