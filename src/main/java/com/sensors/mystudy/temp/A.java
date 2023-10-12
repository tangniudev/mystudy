package com.sensors.mystudy.temp;

import com.alibaba.fastjson.JSON;
import javafx.scene.input.KeyCombination;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class A {
    public static void main(String[] args) throws ParseException {

        byte[] value = null;
        String messageId = new String(value, StandardCharsets.UTF_8);
        System.out.println(messageId);
//        Map<String,String> map = new HashMap<>();
//        Map<String,String> map2 = new HashMap<>();
//        map.put("1","11");
//        map2.put("1","22");
//        map.putAll(map2);
//        System.out.println(map);
//        String s = "jdbc:mysql://hybrid01.classic-tx-beijing-01.org-sep-3536dp1.deploy.sensorsdata.cloud:3305/dingkai_dk_aiobs_webhook_bjyh_db";
//         String[] split = s.split("/");
//        Arrays.stream(split).forEach(System.out::println);
//         String matadata = s.replace(split[split.length - 1], "matadata");
//        System.out.println(matadata);
//
//        final int i = (int) Duration.ofDays(30).getSeconds();
//        System.out.println(i);
//        System.out.println(30 * 24 * 60 * 60 );
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date f = df.parse("2023-05-05 00:00:01");
        Date now = new Date();


//        Date f = df.parse("2023-05-04 23:59:59");


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1); // 将时间回滚一天

        calendar.set(Calendar.HOUR_OF_DAY, 0); // 将时、分、秒和毫秒都设置为0
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date yesterday = calendar.getTime(); // 获取昨天凌晨的时间
        System.out.println(yesterday.getTime());
        System.out.println(f.getTime());

        Map<String, String> map = new HashMap<>();
        map.put("p0", "1");
        map.put("p1", "1");
        map.put("p2", "2");
        map.put("p3", "2");
        System.out.println(JSON.toJSONString(map));
         String s = JSON.toJSONString(map);
         Map map1 = JSON.parseObject(s, Map.class);
        System.out.println(map1);
    }
}
