package com.lezardrieux.back.front.model;

public class Place {

    private String id;
    private String code;
    private int type;

    public Place() {}
    public Place(String id, String code, int type) {
        this.id = id;
        this.code = code;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                '}';
    }
}
