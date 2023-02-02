package com.sensors.mystudy.打断机制;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * Description
 *
 * @author tyw
 * @since 2022/7/19
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {

    }
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(()->{
//            sleep(1);
        }, "t1");
        t1.start();
//        sleep(0.5);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }

}
