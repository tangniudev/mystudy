package com.sensors.mystudy.共享模型之无锁;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/22
 */
public class AtomicIntegerFieldUpdaterTest {
    private volatile int field;
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater fieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "field");
        AtomicIntegerFieldUpdaterTest test5 = new AtomicIntegerFieldUpdaterTest();
        fieldUpdater.compareAndSet(test5, 0, 10);
        // 修改成功 field = 10
        System.out.println(test5.field);
        // 修改成功 field = 20
        fieldUpdater.compareAndSet(test5, 10, 20);
        System.out.println(test5.field);
        // 修改失败 field = 20
        fieldUpdater.compareAndSet(test5, 10, 30);
        System.out.println(test5.field);

    }

    }
