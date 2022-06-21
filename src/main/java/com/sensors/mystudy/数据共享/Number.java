package com.sensors.mystudy.数据共享;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 * 线程八锁问题，考虑synchronizd锁住的对象
 *
 * 修饰普通方法 一个对象中的加锁方法只允许一个线程访问。
 * 但要注意这种情况下锁的是访问该方法的实例对象，
 * 如果多个线程不同对象访问该方法，则无法保证同步。
 *
 * 修饰静态方法 由于静态方法是类方法，
 * 所以这种情况下锁的是包含这个方法的类，也就是类对象；
 * 这样如果多个线程不同对象访问该静态方法，也是可以保证同步的。
 *
 * 修饰代码块 其中普通代码块 如Synchronized（obj）
 * 这里的obj 可以为类中的一个属性、也可以是当前的对象，
 * 它的同步效果和修饰普通方法一样；Synchronized方法
 * （obj.class）静态代码块它的同步效果和修饰静态方法类似。
 */
@Slf4j
class Number {
    public synchronized void a() throws InterruptedException {
        sleep(1000);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) throws InterruptedException {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(() -> {
            try {
                n1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            n2.b();
        }).start();
    }
}

