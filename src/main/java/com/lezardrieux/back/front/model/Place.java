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
    private int avis;
    private double price;
    private String photo;
    private String video;
    private boolean van;
    private boolean water;
    private boolean elect;
    private Member member;

    public Place() {}

    public Place(String id, String code, int type, String comment, int line, int zone, int status, int famous, int avis, double price, String photo, String video, boolean van, boolean water, boolean elect, Member member) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.comment = comment;
        this.line = line;
        this.zone = zone;
        this.status = status;
        this.famous = famous;
        this.avis = avis;
        this.price = price;
        this.photo = photo;
        this.video = video;
        this.van = van;
        this.water = water;
        this.elect = elect;
        this.member = member;
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

    public int getAvis() {
        return avis;
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

    public String getPhoto() {
        return photo;
    }

    public String getVideo() { return video; }

    public Member getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                '}';
    }
}
