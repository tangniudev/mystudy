package com.sensors.mystudy.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
//{"id":"123","name":"jack","birth_dt":null,"age":20}
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

//{"id":"123","name":"jack","birthdt":null,"age":20}
//@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)

//{"id":"123","name":"jack","birth-dt":null,"age":20}
//@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)

//{"Id":"123","Name":"jack","BirthDt":null,"Age":20}
//@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)

@JsonNaming(MyPropertyNamingStrategy.class)


public class TestJsonNamePojo {

    private String id;
    private String na_me;
    private Date birthDt;
    @JsonProperty("JsonAGE")
    private int age;

}
