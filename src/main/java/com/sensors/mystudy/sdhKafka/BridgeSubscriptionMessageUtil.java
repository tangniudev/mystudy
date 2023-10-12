package com.sensors.mystudy.sdhKafka;

import com.alibaba.fastjson.JSONObject;
import com.sensorsdata.common.Literal;
import com.sensorsdata.horizon.v1.BridgeSubscriptionMessage;
import com.google.protobuf.StringValue;
import com.sensorsdata.horizon.v1.EventRecord;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


import java.util.stream.Collectors;

/**
 * @author chenyi@sensorsdata.cn
 * @date 2023/5/25 11:32
 */
@UtilityClass
@Slf4j
public class BridgeSubscriptionMessageUtil {
  public JSONObject parseFastJsonObject(BridgeSubscriptionMessage message) {

    JSONObject jsonObject = new JSONObject();

    // 过滤需要的事件数据
    EventRecord eventRecord = message.getPayload().getEventRecord();

    JSONObject propertiesJsonObject = new JSONObject();
    eventRecord.getPropertiesList().forEach(data -> propertiesJsonObject.put(data.getName(), getValueFromLiteral(data.getPropertyValue())));

    jsonObject.put("distinct_id", eventRecord.getDistinctId());
    jsonObject.put("properties", propertiesJsonObject);
    jsonObject.put("project_id", message.getProjectId());
    jsonObject.put("time", eventRecord.getTime());
    jsonObject.put("recv_time", eventRecord.getReceiveTime());
    jsonObject.put("event", eventRecord.getEvent());
    jsonObject.put("user_id", eventRecord.getUserId());

    return jsonObject;
  }

  private static Object getValueFromLiteral(Literal value) {
    switch (value.getDataType()) {
      case INT:
        return value.getIntValue().getValue();
      case STRING:
        return value.getStringValue().getValue();
      case BOOL:
        return value.getBoolValue().getValue();
      case BIGINT:
        return value.getBigintValue().getValue();
      case DECIMAL:
        return value.getDecimalValue().getValue();
      case DATE:
        return value.getDateValue().getValue();
      case LIST:
        return value.getListValueList().stream().map(StringValue::getValue).collect(Collectors.toList());
      case NUMBER:
        return value.getNumberValue().getValue();
      case DATETIME:
        return value.getDatetimeValue();
      default:
        return null;
    }
  }
}
