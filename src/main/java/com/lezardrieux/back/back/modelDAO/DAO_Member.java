package com.lezardrieux.back.back.modelDAO;

import com.lezardrieux.back.front.model.Member;
import com.lezardrieux.back.front.model.MemberCard;
import com.lezardrieux.back.front.model.MemberPhoto;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "member")
public class DAO_Member {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "surname", length = 20, nullable = false)
    private String surname;
    @Column(name = "photo", columnDefinition = "TEXT")
    private String photo;
    @Column(name = "created")
    private Date created;
    @Column(name = "email", length = 128, nullable = false)         // email Firebase
    private String email;
    @Column(name = "phone", length = 14)
    private String phone;
    @Column(name = "connected")
    private boolean connected;
    @Column(name = "nation")
    private int nation;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "address", length = 128)
    private String address;
    @Column(name = "code", length = 5)
    private String code;
    @Column(name = "city", length = 30)
    private String city;

    //---------------------------------------------------//
    // ONE TO ONE : 1 -> 1
    //---------------------------------------------------//

    @OneToOne(fetch = FetchType.LAZY,
                mappedBy = "member")
    private DAO_Connect connect;

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_Member() {
    }
    public DAO_Member(String name, String surname, String photo, Date created, String email, String phone, boolean connected, int nation, Date birthday, boolean sex, String address, String code, String city) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.created = created;
        this.email = email;
        this.phone = phone;
        this.connected = connected;
        this.nation = nation;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
        this.code = code;
        this.city = city;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public Member getMember() {
        return new Member(  this.id.toString(),
                                this.name,
                                this.surname);
    }

    public MemberPhoto getMemberPhoto() {
        return new MemberPhoto( this.id.toString(),
                                this.name,
                                this.surname,
                                this.photo,
                                this.created);
    }

    public MemberCard getMemberCard() {
        return new MemberCard(  this.id.toString(),
                                this.name,
                                this.surname,
                                this.photo,
                                this.created,
                                this.email,
                                this.phone,
                                this.connected,
                                this.nation,
                                this.birthday,
                                this.sex,
                                this.address,
                                this.code,
                                this.city,
                                this.connect.getConnect());
    }

    public UUID getId() { return id; }
    public String getSId() { return id.toString(); }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public DAO_Member setName(String name) {
        this.name = name;
        return this;
    }

    public DAO_Member setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public DAO_Member setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DAO_Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public DAO_Member setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public DAO_Member setCreated(Date created) {
        this.created = created;
        return this;
    }

    public boolean isConnected() {
        return connected;
    }

    public DAO_Member setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }

    public int getNation() {
        return nation;
    }

    public DAO_Member setNation(int nation) {
        this.nation = nation;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public DAO_Member setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public boolean isSex() {
        return sex;
    }

    public DAO_Member setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public DAO_Member setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DAO_Member setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCity() {
        return city;
    }

    public DAO_Member setCity(String city) {
        this.city = city;
        return this;
    }

    //---------------------------------------------------//
    // ONE TO ONE  GETTER SETTER
    //---------------------------------------------------//

    public DAO_Connect getConnect() {
        return connect;
    }

    public void setConnect(DAO_Connect connect) {
        if (connect == null) {
            if (this.connect != null) {
                this.connect.setMember(null);
            }
        } else {
            connect.setMember(this);
        }
        this.connect = connect;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "DAO_Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
