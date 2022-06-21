package com.sensors.mystudy.数据共享;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 */
class Room {
    int value = 0;
    public void increment() {
        synchronized (this) {
            value++;
        }
    }
    public void decrement() {
        synchronized (this) {
            value--;
        }
    }
    public int get() {
        synchronized (this) {
            return value;
        }
    }
}

