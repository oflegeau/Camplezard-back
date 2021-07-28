package com.lezardrieux.back.front.model;

import javax.persistence.Column;
import java.util.Date;

public class MemberCard extends MemberPhoto {

    private String email;
    private String phone;
    private int nation;
    private Date birthday;
    private String birthdayCity;
    private String profession;
    private boolean sex;
    private String address;
    private String code;
    private String city;
    private String carType;
    private String carNumber;
    private String comment;
    private boolean here;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public MemberCard() {}
    public MemberCard(String id, String name, String surname, String photo, Date created, String email, String phone, int nation, Date birthday, String birthdayCity, String profession, boolean sex, String address, String code, String city, String carType, String carNumber, String comment, boolean here) {
        super(id, name, surname, photo, created);
        this.email = email;
        this.phone = phone;
        this.nation = nation;
        this.birthday = birthday;
        this.birthdayCity = birthdayCity;
        this.profession = profession;
        this.sex = sex;
        this.address = address;
        this.code = code;
        this.city = city;
        this.carType = carType;
        this.carNumber = carNumber;
        this.comment = comment;
        this.here = here;
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

    public String getBirthdayCity() {
        return birthdayCity;
    }

    public String getProfession() {
        return profession;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getComment() {
        return comment;
    }

    public boolean isHere() {
        return here;
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
                                this.getCreated());
    }
}
