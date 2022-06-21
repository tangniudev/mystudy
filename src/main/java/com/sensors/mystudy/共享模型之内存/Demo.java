package com.sensors.mystudy.共享模型之内存;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 *
 * 可见性
 * volatile（易变关键字）
 * 它可以用来修饰成员变量和静态成员变量，他可以避免线程从自己的工作缓存中查找变量的值，必须到主存中获取
 * 它的值，线程操作 volatile 变量都是直接操作主存
 *
 * 有序性
 * JVM 会在不影响正确性的前提下，可以调整语句的执行顺序
 * 多线程下『指令重排』会影响正确性
 * java 并发压测工具 jcstress
 * volatile 修饰的变量，可以禁用指令重排
 */
@Slf4j
public class Demo {
    static volatile boolean run = true;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while(run){
                // ....
               log.debug("aaaa");
            }
        });
        t.start();
        sleep(2);
        run = false; // 线程t不会如预想的停下来
    }
}
