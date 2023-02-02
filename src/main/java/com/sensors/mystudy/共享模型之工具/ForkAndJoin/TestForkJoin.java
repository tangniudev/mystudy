package com.sensors.mystudy.共享模型之工具.ForkAndJoin;

import java.util.concurrent.ForkJoinPool;

/**
 * Description
 *
 * @author tyw
 * @since 2022/7/14
 */
public class TestForkJoin {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        Integer invoke = forkJoinPool.invoke(new MyTask(5));
        System.out.println(invoke);
    }
}
