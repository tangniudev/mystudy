package com.sensors.mystudy.json;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
//        test1();
        TestJsonNamePojo testJsonNamePojo = new TestJsonNamePojo();
        ObjectMapper objectMapper = new ObjectMapper();
        testJsonNamePojo.setId("123");
        testJsonNamePojo.setNa_me("jack");
        testJsonNamePojo.setAge(20);
        System.out.println(objectMapper.writeValueAsString(testJsonNamePojo));


    }

    private static void test1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TestPojo testPojo = new TestPojo();
        testPojo.setId("12345");
        testPojo.setAge(20);
        testPojo.setBirthDt(new Date());
        String value = objectMapper.writeValueAsString(testPojo);
        System.out.println(value);
        TestPojo testPojo2 = new TestPojo();
        testPojo2.setId("12345");
        testPojo2.setAge(20);
        testPojo2.setBirthDt(new Date());

        List<TestPojo> testPojos = new ArrayList<>();
        testPojos.add(testPojo);
        testPojos.add(testPojo2);
        String value1 = objectMapper.writeValueAsString(testPojos);
        System.out.println(value1);

        TestPojo testPojo1 = objectMapper.readValue(value, TestPojo.class);
        System.out.println(testPojo1);

        List<TestPojo> testPojoList = objectMapper.readValue(value1, new TypeReference<List<TestPojo>>() {
        });

        System.out.println(testPojoList);
    }
}
