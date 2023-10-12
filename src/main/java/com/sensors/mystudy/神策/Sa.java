package com.sensors.mystudy.神策;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.EventRecord;
import com.sensorsdata.analytics.javasdk.bean.UserRecord;
import com.sensorsdata.analytics.javasdk.consumer.ConcurrentLoggingConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sa {
    public static void main(String[] args) throws InvalidArgumentException {
        EtlArgs etlArgs = new EtlArgs();
        SensorsAnalytics sa = new SensorsAnalytics(new ConcurrentLoggingConsumer(etlArgs.getOutput()));
        log.info(etlArgs.toString());


        if (!etlArgs.anonymousId.equals("")) {
            log.info("关联id");
            sa.trackSignUp(etlArgs.distinctId, etlArgs.anonymousId);
        } else if (!Boolean.parseBoolean(etlArgs.getIsUser())) {
            log.info("上报事件");
            EventRecord build = EventRecord.builder().setEventName(etlArgs.getEventName())
                    .setDistinctId(etlArgs.getDistinctId())
                    .isLoginId(Boolean.parseBoolean(etlArgs.getIsLogin()))
                    .addProperties(JSON.parseObject(etlArgs.getMap()))
                    .build();
            sa.track(build);
        } else {
            log.info("上报用户");
            UserRecord userRecord = UserRecord.builder().isLoginId(Boolean.parseBoolean(etlArgs.getIsLogin())).setDistinctId(etlArgs.getDistinctId())
                    .addProperties(JSON.parseObject(etlArgs.getMap()))
                    .build();
            sa.profileSet(userRecord);
        }


        sa.flush();


    }

    @Data
    public static class EtlArgs {
        @Parameter(names = {"-a",
                "--anonymousId"}, required = false, description = "关联id")
        private String anonymousId = "";
        @Parameter(names = {"-i", "--input"}, required = false, description = "输入文件路径")
        private String inputPath = "";
        @Parameter(names = {"-o", "--output"}, required = false, description = "输出文件路径")
        private String output = "";
        @Parameter(names = {"id", "--distinct_id"}, required = true, description = "匿名id")
        private String distinctId = "test_001";
        @Parameter(names = {"name", "--eventName"}, required = false, description = "事件名")
        private String eventName = "test";
        @Parameter(names = {"is", "--isLogin"}, required = false, description = "是否登录")
        private String isLogin = "true";
        @Parameter(names = {"m", "--map"}, required = false, description = "其余属性")
        private String map = "";
        @Parameter(names = {"is", "--isUser"}, required = false, description = "是否用户")
        private String isUser = "false";
    }
}
