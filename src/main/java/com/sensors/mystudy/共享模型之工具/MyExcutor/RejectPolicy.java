package com.sensors.mystudy.共享模型之工具.MyExcutor;



/**
 * 自定义拒绝策略接口
 * @param <T>
 */
@FunctionalInterface // 拒绝策略
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

