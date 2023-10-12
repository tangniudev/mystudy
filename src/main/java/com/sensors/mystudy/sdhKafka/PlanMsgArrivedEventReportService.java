package com.sensors.mystudy.sdhKafka;

import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.EventRecord;
import com.sensorsdata.analytics.javasdk.bean.FailedData;
import com.sensorsdata.analytics.javasdk.consumer.FastBatchConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenyi@sensorsdata.cn
 * @date 2022/6/27 11:57
 */
public class PlanMsgArrivedEventReportService {
    private final String url = "http://10.129.19.160:8106/sa?project=default";

    public void report (RewardGrantRequest request) throws Exception {
//        String requestString = "{\"project_id\":1,\"sf_version\":\"4.3.0\",\"channel_category\":\"REWARD_GRANT\",\"channel_id\":77,\"channel_instance_id\":273,\"need_receipt\":true,\"is_test_send\":false,\"messages\":[{\"send_id\":\"17689263020\",\"receipt_properties\":{\"sf_msg_id\":\"msg_id_818_null_1151836096671965298_1656493909939\",\"sf_plan_id\":818,\"sf_plan_name\":\"测试权益发放回执6\",\"sf_enter_plan_time\":1656493909939,\"sf_plan_strategy_id\":0,\"sf_strategy_unit_id\":0,\"sf_plan_type\":\"运营计划\"},\"reward_profile\":{\"reward_id\":\"297\",\"type_id\":\"3\"},\"user_profile\":{\"user_id\":1151836096671965298,\"first_id\":\"350722199509123604\",\"second_id\":null},\"params\":{\"user_name\":\"大侠\"}},{\"send_id\":\"17689263008\",\"receipt_properties\":{\"sf_msg_id\":\"msg_id_818_null_1151836096671948267_1656493909939\",\"sf_plan_id\":818,\"sf_plan_name\":\"测试权益发放回执6\",\"sf_enter_plan_time\":1656493909939,\"sf_plan_strategy_id\":0,\"sf_strategy_unit_id\":0,\"sf_plan_type\":\"运营计划\"},\"reward_profile\":{\"reward_id\":\"297\",\"type_id\":\"3\"},\"user_profile\":{\"user_id\":1151836096671948267,\"first_id\":\"350722199509123513\",\"second_id\":null},\"params\":{\"user_name\":\"刘欢\"}},{\"send_id\":\"17689263018\",\"receipt_properties\":{\"sf_msg_id\":\"msg_id_818_null_1151836096671965296_1656493909939\",\"sf_plan_id\":818,\"sf_plan_name\":\"测试权益发放回执6\",\"sf_enter_plan_time\":1656493909939,\"sf_plan_strategy_id\":0,\"sf_strategy_unit_id\":0,\"sf_plan_type\":\"运营计划\"},\"reward_profile\":{\"reward_id\":\"297\",\"type_id\":\"3\"},\"user_profile\":{\"user_id\":1151836096671965296,\"first_id\":\"350722199509123602\",\"second_id\":null},\"params\":{\"user_name\":\"正灏\"}}]}]";
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//
//        RewardGrantRequest request = objectMapper.readValue(requestString, RewardGrantRequest.class);
//
//        RewardGrantRequest.MessagesDTO message = request.getMessages().get(0);
//
//        request.setChannelId(23);
//        request.setChannelInstanceId(5);
//        message.setSendId("msg_id_80_null_3523849871138324992_1658211519874");
//        message.getReceiptProperties().setSfPlanId(80);
//        message.getReceiptProperties().setSfEnterPlanTime(new Date().getTime());

        //发送失败的数据，收集容器。（仅测试使用，避免网络异常时，大批量数据发送失败同时保存内存中，导致 OOM ）
        final List<FailedData> failedDataList = new ArrayList<>();

        //参数含义：数据接收地址，是否定时上报，非定时上报阈值触发条数，内部缓存最大容量，定时刷新时间间隔，网络请求超时时间，请求失败之后异常数据回调
        //提供多重构造函数，可以根据实际去调用不同的构造函数
        //收集发送失败的数据
        final FastBatchConsumer fastBatchConsumer =
                new FastBatchConsumer(this.url, false, 1, 6000, 2, 3, failedDataList::add);
        //构建 sa 实例对象
        final SensorsAnalytics sa = new SensorsAnalytics(fastBatchConsumer);

        request.getMessages().forEach(message -> {
            //do something track event
            try {
                sa.track(EventRecord.builder()
//                        .setEventName("deposit_saveResult")
//                    .setEventName("csr_callSummary")
//                        .setEventName("csr_orderPush")
                        .setEventName("csr_callInitialization")
                    .setDistinctId(Optional.ofNullable(message.getUserProfile().getSecondId()).orElse("default second id"))
                    .isLoginId(false)
//                                        .addProperty("is_success","true")
//                                        .addProperty("flow_number","1111111")
//                                        .addProperty("channel_name","test_channel_name")
//                                        .addProperty("amount_money",3999)
//                                        .addProperty("product_id",1)
//                                        .addProperty("product_name","小米13")

//                                        .addProperty("message_id","123")
//                                        .addProperty("call_result","true")
//                                        .addProperty("channel_name","test_channel_name")
//                                        .addProperty("seat_id","123")
//                                        .addProperty("seat_name","test_seat_name")

//                                        .addProperty("message_id","1234")
//                                        .addProperty("flow_number","1234")

                                        .addProperty("message_id","12345")
                                        .addProperty("channel_name","test_channel_name")
                                        .addProperty("seat_id","12345")
                                        .addProperty("seat_name","test_seat_name")
//                    .addProperty("$sf_plan_id", message.getReceiptProperties().getSfPlanId())
//                    .addProperty("$sf_plan_strategy_id", message.getReceiptProperties().getSfPlanStrategyId())
//                    .addProperty("$sf_strategy_unit_id", message.getReceiptProperties().getSfStrategyUnitId())
//                    .addProperty("$sf_plan_type", message.getReceiptProperties().getSfPlanType())
//                    .addProperty("$sf_channel_id", request.getChannelInstanceId())
//                    .addProperty("$sf_channel_category", request.getChannelCategory())
//                    .addProperty("$sf_enter_plan_time", message.getReceiptProperties().getSfEnterPlanTime())
//                    .addProperty("$sf_msg_id", message.getReceiptProperties().getSfMsgId())
//                    .addProperty("$sf_msg_status", "RECEIPT_SEND_FAILED")
//                    //.addProperty("$sf_msg_status", "RECEIPT_SEND_SUCCESS")
//                    .addProperty("$sf_send_fail_code", "302")
//                    .addProperty("$sf_fail_reason", "testReason02")
                    .build());
            } catch (InvalidArgumentException e) {
                throw new RuntimeException(e);
            }
        });

        sa.flush();

        //异常数据的重发送设置(重发送尽量由另外一个单独的线程来处理完成，避免影响主线程处理逻辑)
        if (!failedDataList.isEmpty()) {
            for (FailedData failedData : failedDataList) {
                try {
                    //返回重发送接口发送成功与否，true:发送成功；false:发送失败
                    boolean b = fastBatchConsumer.resendFailedData(failedData);
                } catch (Exception e) {
                    //处理重发送数据校验异常的情况
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        RewardGrantRequest request = new RewardGrantRequest();
        request.setChannelId(53);
        request.setChannelInstanceId(8);
        request.setChannelCategory("REWARD_GRANT");

        RewardGrantRequest.MessagesDTO message = new RewardGrantRequest.MessagesDTO();
        RewardGrantRequest.MessagesDTO.ReceiptPropertiesDTO propertiesDTO = new RewardGrantRequest.MessagesDTO.ReceiptPropertiesDTO();
        propertiesDTO.setSfEnterPlanTime(new Date().getTime());
        propertiesDTO.setSfMsgId("mock0003");
        propertiesDTO.setSfPlanId(43);
        propertiesDTO.setSfPlanStrategyId(0);
        propertiesDTO.setSfPlanType("运营计划");
        propertiesDTO.setSfStrategyUnitId(0);

        message.setReceiptProperties(propertiesDTO);


        message.setUserProfile(new RewardGrantRequest.MessagesDTO.UserProfileDTO());


        request.setMessages(Stream.of(message).collect(Collectors.toList()));

        new PlanMsgArrivedEventReportService().report(request);
    }
}
