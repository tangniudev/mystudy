package com.sensors.mystudy.JVM;

public class DeadThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName()+"开始");
            DeadThread deadThread = new DeadThread();
            System.out.println("结束");
        };
         Thread thread = new Thread(r, "线程一");
        Thread thread2 = new Thread(r, "线程二");
        thread.start();
        thread2.start();
    }



}

class DeadThread{
    static {
        if (true){
            System.out.println(Thread.currentThread().getName()+"开始初始化");//初始化会上锁，保障只执行一次
            while (true){

            }
        }
    }
}
