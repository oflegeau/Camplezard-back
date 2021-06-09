package com.lezardrieux.back.front.model;

import java.util.Date;

public class MemberPhoto extends Member {

    String photo;                           // from DataBase - source
    Date created;                           // from DataBase - source

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public MemberPhoto() {}
    public MemberPhoto(String id, String name, String surname, String photo, Date created) {
        super(id, name, surname);
        this.photo = photo;
        this.created = created;
    }

    //---------------------------------------------------//
    // GET
    //---------------------------------------------------//

    public Member getMember() {
        return new Member(  this.id,
                            this.name,
                            this.surname);
    }

    public String getPhoto() {
        return photo;
    }

    public Date getCreated() {
        return created;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "MemberPhoto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
