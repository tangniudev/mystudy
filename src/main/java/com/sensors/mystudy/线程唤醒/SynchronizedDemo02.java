package com.sensors.mystudy.线程唤醒;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 * synchronized是不可重入锁，被其他线程持有了就必须等待其他线程释放
 */
@Slf4j
public class SynchronizedDemo02 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {//持有room对象的锁，等到这个代码块执行完毕释放room对象锁
                log.debug("有烟没？[{}]", hasCigarette);
                if (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        room.wait();//先把room对象锁释放出去
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("可以开始干活了");
                }
            }
        }, "小南").start();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {//需要先判断是否能持有这个对象
                    log.debug("可以开始干活了");
                }
            }, "其它人").start();

        }
        sleep(1000);
        new Thread(() -> {
            // 这里能不能加 synchronized (room)？
            hasCigarette = true;
            log.debug("烟到了噢！");
        }, "送烟的").start();

    }
}
