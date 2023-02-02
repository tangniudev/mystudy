package com.sensors.mystudy.阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/7/18
 *
 * 方法类型	抛出异常	特殊值(有返回值)	阻塞	    超时
 * 插入	    add	    offer	        put	    offer
 * 移除	    remove	poll	        take	poll
 * 判断队列首	element	peek	        -	    -
 */
public class Demo {

    public static void main(String[] args) {

        test4();

    }
    /**
     * 抛出异常
     */
    public static void test1(){
        //指定队列大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
//        System.out.println(arrayBlockingQueue.element());//没有队首元素就会报错
        //add添加成功返回true
        System.out.println(arrayBlockingQueue.add("1"));
        System.out.println(arrayBlockingQueue.add("2"));
        System.out.println(arrayBlockingQueue.add("3"));
//        System.out.println(arrayBlockingQueue.add("4"));//超过了队列最大容量就会报错
        //查看队首的元素是谁 1
        System.out.println(arrayBlockingQueue.element());
        //超过队列大小 add会抛出异常  Queue full
//        System.out.println(arrayBlockingQueue.add("4"));
        //remove取出一个元素  返回取出的值   如果队列为空  remove会抛出异常
        // NoSuchElementException
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
    }

    /**
     * 不报错，没有就返回空
     */
    public static void test2(){
        //队列的大小
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(blockingQueue.peek());
        //offer  添加一个元素  返回一个boolean值   成功返回true失败返回true
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        System.out.println("----------------");
        //检测队首元素
        System.out.println(blockingQueue.peek());
        //poll  取出一个元素  返回一个元素    队列为空时 取出null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 等待 一直阻塞
     */
    public static void test3(){
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(2);

        try {
            //put添加元素 没有返回值 满了一直阻塞
            //队列大小为二   第三个元素放不进去   阻塞两秒过后就会结束
            blockingQueue.put("1");
            blockingQueue.put("2");
//            blockingQueue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //取出元素  空了一直阻塞  返回值取出的元素
            System.out.println(blockingQueue.take());;
            System.out.println(blockingQueue.take());
//            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待  超时阻塞
     */
    public static void test4(){
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(2);

        try {
            //参数 插入的数值  超时时间 和 单位
            blockingQueue.offer("1");
            blockingQueue.offer("2");
            blockingQueue.offer("3",2, TimeUnit.SECONDS);
            System.out.println("------");
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
