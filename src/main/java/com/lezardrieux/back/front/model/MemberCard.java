package com.lezardrieux.back.front.model;

import java.util.Date;

public class MemberCard extends MemberPhoto {

    private String email;                           // from DataBase - source
    private String phone;                           // from DataBase - source
    private boolean connected;                      // build
    private int nation;
    private Date birthday;
    private boolean sex;                        // from DataBase - source
    private String address;                     // from DataBase - source
    private String code;                        // from DataBase - source
    private String city;                        // from DataBase - source
    private Connect connect;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public MemberCard() {}

    public MemberCard(String id, String name, String surname, String photo, Date created, String email, String phone, boolean connected, int nation, Date birthday, boolean sex, String address, String code, String city, Connect connect) {
        super(id, name, surname, photo, created);
        this.email = email;
        this.phone = phone;
        this.connected = connected;
        this.nation = nation;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
        this.code = code;
        this.city = city;
        this.connect = connect;
    }

    //---------------------------------------------------//
    // GET
    //---------------------------------------------------//

    public Member getMember() {
        return new Member(  this.id,
                            this.name,
                            this.surname);
    }

    public MemberPhoto getMemberPhoto() {
        return new MemberPhoto( this.id,
                                this.name,
                                this.surname,
                                this.photo,
                                this.created);
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getNation() {
        return nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public boolean isConnected() {
        return connected;
    }

    public Connect getConnect() {
        return connect;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "MemberCard{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
