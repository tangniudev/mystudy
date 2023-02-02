package com.sensors.mystudy.自定义注解;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface MyAnnotation {
    String value() default "";

    String add() default "";
}
