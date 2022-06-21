package com.sensors.mystudy.数据共享.转账联系;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 */
@Slf4j
public class ExerclseTransfer {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // 查看转账2000次后的总金额
        log.debug("total:{}", (a.getMoney() + b.getMoney()));//转账金额最终能不能一直还是要看转账过程中对共享元素是否互斥操作，锁对象是否为同一个
    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机 1~100
    public static int randomAmount() {
        return random.nextInt(100) + 1;
    }
}


