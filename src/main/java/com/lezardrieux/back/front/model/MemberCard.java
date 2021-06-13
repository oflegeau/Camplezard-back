package com.lezardrieux.back.front.model;

import java.util.Date;

public class MemberCard extends MemberPhoto {

    private String email;
    private String phone;
    private int nation;
    private Date birthday;
    private boolean sex;
    private String address;
    private String code;
    private String city;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public MemberCard() {}
    public MemberCard(String id, String name, String surname, String photo, Date created, boolean connected, String email, String phone, int nation, Date birthday, boolean sex, String address, String code, String city) {
        super(id, name, surname, photo, created, connected);
        this.email = email;
        this.phone = phone;
        this.nation = nation;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
        this.code = code;
        this.city = city;
    }

    //---------------------------------------------------//
    // GET
    //---------------------------------------------------//

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

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "MemberCard{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nation=" + nation +
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

    public MemberPhoto getMemberPhoto() {
        return new MemberPhoto( this.getId(),
                                this.getName(),
                                this.getSurname(),
                                this.getPhoto(),
                                this.getCreated(),
                                this.isConnected());
    }
}
