package com.sensors.mystudy.sdhKafka;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sensorsdata.horizon.v1.BridgeSubscriptionMessage;
import com.sun.deploy.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @auther Codievilky August
 * @since 2019/1/15
 */
@Slf4j
public class KafkaClient {
    private KafkaConsumer<String, byte[]> consumer;
    private Map<String, Long> lastOffset = new HashMap<>();

    public KafkaClient(String[] brokerList) {
        consumer = getKafkaConsumer(brokerList);
    }

    /**
     * 通过 kafka 的 broker 列表获取消费者
     *
     * @param brokerList kafka broker 的列表
     */
    private KafkaConsumer<String, byte[]> getKafkaConsumer(String[] brokerList) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, StringUtils.join(Arrays.asList(brokerList), ","));
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "testEventGroupId22");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, RandomStringUtils.randomAlphabetic(10));
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // 可填写两个值 latest 与 earliest。分别代表，最新与最早的数据
        return new KafkaConsumer<>(properties);
    }

    /**
     * 通过指定 topic 的方式来订阅数据，订阅进度会有 kafka 来维护
     *
     * @param topicNames 需要消费的 topic 名称的集合
     */
    public void assignByTopic(Collection<String> topicNames) {
        consumer.subscribe(topicNames);
    }

    /**
     * 获取指定超时时间获取数据
     *
     * @param timeoutMillis 超时时间
     */
    public List<byte[]> pollRecords(int timeoutMillis) {
        ConsumerRecords<String, byte[]> consumerRecords = consumer.poll(Duration.ofMillis(timeoutMillis)); // NOSONAR
        ArrayList<byte[]> records = new ArrayList<>();
        consumerRecords.partitions().forEach(partition -> {
            AtomicBoolean ifUpdate = new AtomicBoolean(false);
            List<ConsumerRecord<String, byte[]>> recordsWithPartition = consumerRecords.records(partition);

            long newOffset = recordsWithPartition.get(recordsWithPartition.size() - 1).offset();
            try {
                BridgeSubscriptionMessage message = BridgeSubscriptionMessage.parseFrom(recordsWithPartition.get(0).value());
                JSONObject jsonObject = BridgeSubscriptionMessageUtil.parseFastJsonObject(message);
                System.out.println("订阅的数据"+ jsonObject);
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
            String value = new String(recordsWithPartition.get(0).value(), StandardCharsets.UTF_8);

            if (lastOffset.getOrDefault(partition.toString(), 0L) < newOffset) {
                lastOffset.put(String.valueOf(partition), newOffset);
                ifUpdate.set(true);
            }
            Map<TopicPartition, OffsetAndMetadata> newOffsetMap = new HashMap<>();
            // 更新offset，即上一次最大的offset + 1
            newOffsetMap.put(partition, new OffsetAndMetadata(newOffset + 1));
            consumer.commitSync(newOffsetMap);
            if (ifUpdate.get()) {
                recordsWithPartition.forEach(record ->
                        records.add(record.value())
                );
            } else {
                log.warn("当前最新的offset为 {},上次offset为 {},可能造成重复,将无返回值", newOffset, lastOffset.toString());
            }
        });
        //即使说提交失败，也不会影响返回，那么这就可能造成重复
        //consumer.commitSync()
        return records;
    }
}