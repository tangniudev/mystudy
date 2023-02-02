package com.sensors.mystudy.自定义注解;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Demo {


    public static void main(String[] args) throws Exception{
        User user = new User();
        System.out.println(user);
//        Field name = user.getClass().getDeclaredField("name");
//        name.setAccessible(true);
//        name.set(user,"asdsdasd");
//        System.out.println(user);

        Field[] fields = User.class.getDeclaredFields();
//         Arrays.stream(fields).filter(field -> field.isAnnotationPresent(MyAnnotation.class))
//                 .map(field -> {
//                     field.setAccessible(true);
//                     String value = field.getAnnotation(MyAnnotation.class).value();
//                     System.out.println(value);
//                     try {
//                         field.set(user,value);
//                     } catch (IllegalAccessException e) {
//                         e.printStackTrace();
//                     }
//                     System.out.println(user);
//                     return field;
//                 });
        for (Field field : fields){
            if (field.isAnnotationPresent(MyAnnotation.class)){
                field.setAccessible(true);
                Class<?> type = field.getType();
                System.out.println(type);
                if (type.getName().equals("int")){
                    field.set(user,Integer.valueOf( field.getAnnotation(MyAnnotation.class).value()));
                }else {
                    field.set(user, field.getAnnotation(MyAnnotation.class).value());
                }
                field.setAccessible(false);
            }

        }
        System.out.println(user);

        Method[] methods = user.getClass().getDeclaredMethods();
        for (Method method : methods){
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            if (annotation != null){
                String add = annotation.add();
                System.out.println(add);
                method.invoke(user);
                System.out.println(add);
            }
        }


    }
}
