package com.sensors.mystudy.数据共享;

import java.util.ArrayList;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 *
 * 方法访问修饰符带来的思考，如果把 method2 和 method3 的方法修改为 public 会不会代理线程安全问题？
 * 情况1：有其它线程调用 method2 和 method3
 * 情况2：在 情况1 的基础上，为 ThreadSafe 类添加子类，子类覆盖 method2 或 method3 方法，即
 */
class ThreadUnsafe {
    ArrayList<String> list = new ArrayList<>();//list是公共区域变量

    public void method1(int loopNumber) {//method1方法有可能被继承修改
        for (int i = 0; i < loopNumber; i++) {
            // { 临界区, 会产生竞态条件
            method2();
            method3();
//        }临界区
        }
    }

    private void method2() {
        list.add("1");
    }

    private void method3() {
        list.remove(0);
    }
}