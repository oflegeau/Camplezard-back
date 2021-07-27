package com.lezardrieux.back.front.model;

public class Place {

    private String id;
    private String code;
    private int type;
    private String comment;
    private int line;
    private int zone;
    private int status;
    private int famous;
    private double price;
    private boolean van;
    private boolean water;
    private boolean elect;

    public Place() {}
    public Place(String id, String code, int type, String comment, int line, int zone, int status, int famous, double price, boolean van, boolean water, boolean elect) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.comment = comment;
        this.line = line;
        this.zone = zone;
        this.status = status;
        this.famous = famous;
        this.price = price;
        this.van = van;
        this.water = water;
        this.elect = elect;
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public String getComment() {
        return comment;
    }

    public boolean isVan() {
        return van;
    }

    public boolean isWater() {
        return water;
    }

    public boolean isElect() {
        return elect;
    }

    public int getLine() {
        return line;
    }

    public int getZone() {
        return zone;
    }

    public int getStatus() {
        return status;
    }

    public int getFamous() {
        return famous;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                '}';
    }
}
