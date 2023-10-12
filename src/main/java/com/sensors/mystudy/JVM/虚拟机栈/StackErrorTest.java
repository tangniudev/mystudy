package com.sensors.mystudy.JVM.虚拟机栈;

public class StackErrorTest {
    private static int i = 0 ;
    public static void main(String[] args) throws InterruptedException {
//        int i ;
        System.out.println(i++);
//        main(args);
//        System.out.println(args[0]);

        Thread.sleep(10000000);
    }
}
