package com.sensors.mystudy.sdhKafka;

import com.alibaba.fastjson.JSONObject;
import com.sensorsdata.horizon.v1.BridgeSubscriptionMessage;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTest {
    public static void main(String[] args) {
        String[] brokerlist = {"10.129.19.160:9092","10.129.29.20:9092","10.129.6.205:9092"};
        String topic = "infinity_bridge_subscription_event_stream";
         KafkaClient kafkaClient = new KafkaClient(brokerlist);
         kafkaClient.assignByTopic(Collections.singleton(topic));
//         List<byte[]> bytes = kafkaClient.pollRecords(1000);
//         bytes.stream().map(bytes1 -> new String(.))
        List<byte[]> records;
        while (true) {
            records = kafkaClient.pollRecords((int) Duration.ofSeconds(10).toMillis()); // 订阅数据
            for (byte[] s : records) {
                try {

                    BridgeSubscriptionMessage message = BridgeSubscriptionMessage.parseFrom(s);
                    JSONObject jsonObject = BridgeSubscriptionMessageUtil.parseFastJsonObject(message);
                    System.out.println("订阅的数据"+ jsonObject);
//                     String s1 = new String(s, StandardCharsets.UTF_8);
//                    System.out.println(s1);
                }catch (Exception e){
//                    log.error("", e);
                }
            }
        }
    }
}
