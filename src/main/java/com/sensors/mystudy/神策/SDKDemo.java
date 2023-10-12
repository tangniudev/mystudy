package com.sensors.mystudy.神策;

import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.EventRecord;
import com.sensorsdata.analytics.javasdk.bean.UserRecord;
import com.sensorsdata.analytics.javasdk.consumer.BatchConsumer;
import com.sensorsdata.analytics.javasdk.consumer.ConcurrentLoggingConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

import java.util.HashMap;

public class SDKDemo {
    public static void main(String[] args) throws InvalidArgumentException {
        trackId();
//        String str = "\"test\"";
//        System.out.println(str);
//        System.out.println(str.replace("\"",""));
    }
//    select * from users limit 1;
//
//    select * from users where first_id = 'test_id99999'
//
//    select * from users where first_id = 'test_id99998'
//
//    select * from users where first_id = '“test_id99997”'
//    select * from users where first_id = '"test_id99997"'
//    select * from users where first_id = 'test_id99997'
//
//    select * from users where $device_id_list is not null;


    public static void trackId() throws InvalidArgumentException {
        // 从神策分析获取的数据接收的 URL
        final String SA_SERVER_URL = "http://10.129.18.14:8106/sa?project=default";
// 当缓存的数据量达到50条时，批量发送数据
        final int SA_BULK_SIZE = 1;
// 数据同步失败不抛出异常
        final boolean THROW_EXCEPTION = false;
// 内存中数据最大缓存条数，如此值大于0，代表缓存的数据会有条数限制，最小 3000 条，最大 6000 条。否则无条数限制。
        final int MAX_CACHE_SIZE = 0;
// 使用 BatchConsumer 初始化 SensorsAnalytics
// 不要在任何线上的服务中使用此 Consumer
//        final SensorsAnalytics sa = new SensorsAnalytics(new BatchConsumer(SA_SERVER_URL, SA_BULK_SIZE, MAX_CACHE_SIZE, THROW_EXCEPTION));
        SensorsAnalytics sa = new SensorsAnalytics(new ConcurrentLoggingConsumer("D:\\data\\service", "D:\\data\\servicesa.lock"));
        UserRecord test_id98 = UserRecord.builder().isLoginId(true).setDistinctId("test_2").build();
//         sa.profileDelete(test_id98);
//        sa.trackSignUp();
            sa.profileSet(test_id98);
            sa.profileSet("tesi_001",false,new HashMap<>());
//        sa.trackSignUp("test_1","test_5");
         EventRecord build = EventRecord.builder()
                 .setDistinctId("ffd")
                 .isLoginId(Boolean.TRUE)
                 .addProperty("asd","asfsdf")
                 .setEventName("test")
                 .build();
        sa.track(build);
        sa.flush();
    }
}
