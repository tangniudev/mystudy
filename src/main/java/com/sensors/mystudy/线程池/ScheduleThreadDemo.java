package com.sensors.mystudy.线程池;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.schedule(()-> {
//            try {
//                System.out.println(System.currentTimeMillis() / 1000);
//                Thread.sleep(3000);
//                System.out.println(System.currentTimeMillis() / 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },2, TimeUnit.MILLISECONDS);

//        scheduledThreadPool.scheduleWithFixedDelay(()-> {
//            try {
//                LocalDateTime localDateTime = LocalDateTime.now();
//                System.out.println(Thread.currentThread().getName()+ "开始时间"+  localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                Thread.sleep(1000);
//                LocalDateTime localDateTime2 = LocalDateTime.now();
////                System.out.println(Thread.currentThread().getName()+ "结束时间"+  localDateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },0,10, TimeUnit.SECONDS);


//        scheduledThreadPool.scheduleAtFixedRate(()-> {
//            try {
//                LocalDateTime localDateTime = LocalDateTime.now();
//                System.out.println(Thread.currentThread().getName()+ "开始时间"+  localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                Thread.sleep(1000);
//                LocalDateTime localDateTime2 = LocalDateTime.now();
////                System.out.println(Thread.currentThread().getName()+ "结束时间"+  localDateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },0,10, TimeUnit.SECONDS);
//        LocalDateTime Time = LocalDateTime.now();
//        System.out.println(Thread.currentThread().getName() + "开始时间" + Time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        for (int i = 0;i<10;i++) {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(Thread.currentThread().getName() + "开始时间" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            Thread.sleep(i * 1000);
            scheduledThreadPool.schedule(() -> {
                try {
                    Thread.sleep(1000);
                    LocalDateTime localDateTime2 = LocalDateTime.now();
                System.out.println(Thread.currentThread().getName()+ "结束时间"+  localDateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 5, TimeUnit.SECONDS);
        }

        System.out.println("2222222");
    }

}
