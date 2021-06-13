package com.lezardrieux.back.front.model;

import java.util.Date;

public class MemberPhoto extends Member {

    private String photo;                           // from DataBase - source
    private Date created;                           // from DataBase - source
    private boolean connected;              // build

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public MemberPhoto() {}
    public MemberPhoto(String id, String name, String surname, String photo, Date created, boolean connected) {
        super(id, name, surname);
        this.photo = photo;
        this.created = created;
        this.connected = connected;
    }
    //---------------------------------------------------//
    // GET
    //---------------------------------------------------//

    public String getPhoto() {
        return photo;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isConnected() {
        return connected;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "MemberPhoto{" +
                "photo='" + photo + '\'' +
                ", created=" + created +
                ", connected=" + connected +
                '}';
    }

    //---------------------------------------------------//
    // getFront
    //---------------------------------------------------//

    public Member getMember() {
        return new Member(  this.getId(),
                            this.getName(),
                            this.getSurname());
    }

}
