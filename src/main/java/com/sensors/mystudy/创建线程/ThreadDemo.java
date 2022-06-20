package com.sensors.mystudy.创建线程;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/20
 */
@Slf4j
public class ThreadDemo {
    public static void main(String[] args) {
        method01();
        method02();
        try {
            method03();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //直接使用Thread创建使得线程和任务合并在一起了
    private static void method01() {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.info("直接使用thread方式创建线程");
            }
        };
        t1.start();
    }
    //直接使用Thread创建使得线程和任务分离，便于后期与线程池的高级api融合
    private static void method02() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("使用runnable接口创建线程");
            }
        };
        Thread t2 = new Thread(runnable, "t2");
        t2.start();
    }
//    FutureTask 能够接收 Callable 类型的参数，用来处理有返回结果的情况
    private static void method03() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            log.debug("使用futureTask创建线程");
            return 100;
        });
        Thread t3 = new Thread(futureTask, "t3");
        t3.start();
        Integer integer = futureTask.get();
        log.debug("返回的结果是" + integer);
    }
}
