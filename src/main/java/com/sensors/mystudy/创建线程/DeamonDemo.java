package com.sensors.mystudy.创建线程;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/20
 * 非守护线程只睡了一秒，守护线程睡了两秒，但是1秒后所有的非守护线程结束了，守护线程也强制结束
 */
@Slf4j
public class DeamonDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread daemon = new Thread(() -> {
            log.debug("开始");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");//非守护线程只睡了一秒，守护线程睡了两秒，但是1秒后所有的非守护线程结束了，守护线程也强制结束，所以该语句不会打印
        }, "daemon");
        daemon.setDaemon(true);
        daemon.start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("运行结束");
    }
}
