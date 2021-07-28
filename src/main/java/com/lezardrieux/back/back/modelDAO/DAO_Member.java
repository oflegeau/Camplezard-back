package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "surname", length = 20)
    private String surname;
    @Column(name = "photo", columnDefinition = "TEXT")
    private String photo;
    @Column(name = "created")                                         // created Firebase
    private Date created;
    @Column(name = "here")
    private boolean here;

    @Column(name = "email", length = 128, nullable = false)         // email Firebase
    private String email;
    @Column(name = "phone", length = 14)                             // phone Firebase
    private String phone;
    @Column(name = "nation")
    private int nation;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "birthday_city", length = 30)
    private String birthdayCity;
    @Column(name = "profession", length = 30)
    private String profession;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "address", length = 128)
    private String address;
    @Column(name = "code", length = 5)
    private String code;
    @Column(name = "city", length = 30)
    private String city;
    @Column(name = "car_type",length = 20)
    private String carType;
    @Column(name = "car_number",length = 10)
    private String carNumber;
    @Column(name = "comment", length = 255)
    private String comment;

    //---------------------------------------------------//
    // ONE TO ONE : 1 -> 1
    //---------------------------------------------------//

    @OneToMany( mappedBy = "member",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_Resa> resas = new ArrayList<>();

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_Member() {}
    public DAO_Member(String name, String surname, String photo, Date created, boolean here, String email, String phone, int nation, Date birthday, String birthdayCity, String profession, boolean sex, String address, String code, String city, String carType, String carNumber, String comment) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.created = created;
        this.here = here;
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
    }
//---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public UUID getId() { return id; }

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

    public boolean isHere() {
        return here;
    }

    public DAO_Member setHere(boolean here) {
        this.here = here;
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

    public DAO_Member setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getBirthdayCity() {
        return birthdayCity;
    }

    public DAO_Member setBirthdayCity(String birthdayCity) {
        this.birthdayCity = birthdayCity;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public DAO_Member setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public String getCarType() {
        return carType;
    }

    public DAO_Member setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public DAO_Member setCarNumber(String carNumber) {
        this.carNumber = carNumber;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public DAO_Member setComment(String comment) {
        this.comment = comment;
        return this;
    }

    //---------------------------------------------------//


    public List<DAO_Resa> getResas() {
        return resas;
    }

    public DAO_Member setResas(List<DAO_Resa> resas) {
        this.resas = resas;
        return this;
    }

    public DAO_Resa addResa(DAO_Resa Tobj) {
        resas.add(Tobj);
        Tobj.setMember(this);
        return Tobj;
    }
    public DAO_Resa detachResa(DAO_Resa Tobj) {
        Tobj.setMember(null);
        return Tobj;
    }
    public DAO_Resa removeResa(DAO_Resa Tobj) {
        resas.remove(Tobj);
        Tobj.setMember(null);
        return Tobj;
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

    //---------------------------------------------------//
    // getFront
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
                                this.nation,
                                this.birthday,
                                this.birthdayCity,
                                this.profession,
                                this.sex,
                                this.address,
                                this.code,
                                this.city,
                                this.carType,
                                this.carNumber,
                                this.comment,
                                this.here);
    }

}
