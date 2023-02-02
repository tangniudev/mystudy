package com.sensors.mystudy.打断机制;

import lombok.extern.slf4j.Slf4j;

/**
 * Description
 *
 * @author tyw
 * @since 2022/7/19
 *
 * 方法	                                方法描述
 * public static boolean interrupted()	判断当前线程的中断标志位是否为true，并清除中断标志位，重置为fasle,当前线程！！！！
 * public boolean isInterrupted()	    判断当前线程的中断标志位是否为true，不会清除中断标志位
 * public void interrupt()	            将线程的中断标志位设置为true，不会停止线程
 */
@Slf4j
public class ThreadInterruptTest {
    // 计数器
    static int i = 0;
    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                System.out.println("线程启动了");
                while (!isInterrupted()) {
                    System.out.println(isInterrupted());//调用 interrupt 之后为true
                }
                System.out.println("线程结束了");
            }
        };
        thread.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        System.out.println("线程是否被中断：" + thread.isInterrupted());//true
        thread.interrupt();

        System.out.println(thread.interrupted());//interrupted是静态方法，只针对当前线程,应该这样使用 Thread.interrupted();
        System.out.println(thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());//isInterrupted是实例方法，针对调用者的中断标志
        System.out.println(Thread.currentThread().interrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().interrupted());
    }
}
