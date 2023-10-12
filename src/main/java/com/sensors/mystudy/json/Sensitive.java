package com.sensors.mystudy.json;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //作用于字段上
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside  // 表示自定义自己的注解Sensitive
@JsonSerialize(using = SensitiveInfoSerialize.class) // 该注解使用序列化的方式
public @interface Sensitive {
    SensitizedType value();
}

//    @ApiOperation(value = "脱敏测试处理")
//    @GetMapping("sensitiveTest")
//    public AjaxResult sensitiveTest(){
//        SensitiveBody body = new SensitiveBody();
//        body.setMobile("13041064026");
//        body.setIdCard("411126189912355689");
//        body.setName("Tom");
//
//        return AjaxResult.success(body);
//    }
//
//
//    @ApiOperation(value = "脱敏测试处理")
//    @GetMapping("sensitiveTest")
//    public AjaxResult sensitiveTest(){
//        SensitiveBody body = new SensitiveBody();
//        body.setMobile("13041064026");
//        body.setIdCard("411126189912355689");
//        body.setName("Tom");
//
//        return AjaxResult.success(body);
//    }


