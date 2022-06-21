package com.sensors.mystudy.共享模型之内存;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/21
 */
public class TestVolatile {
    volatile boolean initialized = false;
    public void init() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }
    private void doInit() {
        System.out.println("aa");
    }

    public static void main(String[] args) {
        TestVolatile testVolatile = new TestVolatile();
        testVolatile.init();
    }
}
