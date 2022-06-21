package com.sensors.mystudy.数据共享;

import java.util.ArrayList;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 *
 * 常见线程安全类
 * String
 * Integer
 * StringBuffer
 * Random
 * Vector
 * Hashtable
 * java.util.concurrent 包下的类
 *
 * 这里说它们是线程安全的是指，多个线程调用它们同一个实例的某个方法时，是线程安全的。也可以理解为
 * 它们的每个方法是原子的
 * 但注意它们多个方法的组合不是原子的，见后面分析
 */
class ThreadSafe {
    public final void method1(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }
    private void method2(ArrayList<String> list) {
        list.add("1");
    }
    private void method3(ArrayList<String> list) {
        list.remove(0);
    }
}
