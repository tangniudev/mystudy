package com.sensors.mystudy.共享模型之工具.ForkAndJoin;

import java.util.concurrent.RecursiveTask;

/**
 * Description
 *
 * @author tyw
 * @since 2022/7/14
 */
public class MyTask extends RecursiveTask<Integer> {
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1)
            return 1;
        MyTask myTask = new MyTask(n - 1);
        //让一个新的线程去执行此任务
        myTask.fork();
        int result = n + myTask.join();//获取执行任务结果
        return result;
    }
}
