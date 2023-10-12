package com.sensors.mystudy.easyExcel;

import lombok.Data;

@Data
public class FieldInfo {
    String from;
    String to;
    String type;
    Boolean isUpdateforInsert;
}
