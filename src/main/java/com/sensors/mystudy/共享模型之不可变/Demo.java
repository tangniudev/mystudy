package com.sensors.mystudy.共享模型之不可变;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/22
 * 由于 SimpleDateFormat 不是线程安全的
 * 有很大几率出现 java.lang.NumberFormatException 或者出现不正确的日期解析结果，例如：
 * 思路 - 同步锁
 *synchronized 这样虽能解决问题，但带来的是性能上的损失，并不算很好：
 * 思路 - 不可变
 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
//                synchronized (sdf) {
                    try {
                        log.debug("{}", sdf.parse("1951-04-21"));
                    } catch (Exception e) {
                        log.error("{}", e);
                    }
//                }

            }).start();
        }

    }
}
