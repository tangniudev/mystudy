package com.sensors.mystudy.共享模型之工具.MyExcutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/22
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2,
                1000, TimeUnit.MILLISECONDS, 2, (queue, task)->{
            // 1. 死等
// queue.put(task);
            // 2) 带超时等待
//            try {
                queue.offer(task, 1500, TimeUnit.MILLISECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            // 3) 让调用者放弃任务执行
// log.debug("放弃{}", task);
            // 4) 让调用者抛出异常
// throw new RuntimeException("任务执行失败 " + task);
            // 5) 让调用者自己执行任务
//            task.run();
        });
        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}", j);
            });
        }
    }
}
