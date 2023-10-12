package com.sensors.mystudy.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class MyPropertyNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {
    @Override
    public String translate(String propertyName) {

        return propertyName.toUpperCase();
    }
}
