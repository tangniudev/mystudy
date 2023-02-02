package com.sensors.mystudy.自定义注解;


import lombok.Data;

@Data
public class User {

    @MyAnnotation("aaa")
    private  String name;
    @MyAnnotation("10")
    private  int age;

    @MyAnnotation(add = "bilibili")
    public  void method(){
        System.out.println("用户自己的方法");
    }
    public  void method2(){
        System.out.println("用户自己的方法2");
    }
}
