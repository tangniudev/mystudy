package com.sensors.mystudy.chatGpt;

public class ChessPiece {
    private String color;
    private String type;

    public ChessPiece(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
