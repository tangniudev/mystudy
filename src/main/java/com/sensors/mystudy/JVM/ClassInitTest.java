package com.sensors.mystudy.JVM;

public class ClassInitTest {
    private static int m = 10;
    static {
        m = 20;
        n = 5;

        System.out.println(m);
//        System.out.println(n);//报错非法的前向引用
    }
    private static int n = 10; //link之prepare n = 0, init n = 5  -> 10
    public static void main(String[] args) {
        System.out.println(m);
        System.out.println(n);
    }
}
