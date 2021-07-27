package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lezardrieux.back.front.model.MemberWith;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "member_with")
public class DAO_MemberWith {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "surname", length = 20)
    private String surname;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "birthday")
    private Date birthday;

    //---------------------------------------------------//
    // MANY TO ONE : N -> 1 (Listes)
    //---------------------------------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DAO_Resa resa;

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_MemberWith() {}
    public DAO_MemberWith(String name, String surname, boolean sex, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public UUID getId() {
        return id;
    }

    public DAO_MemberWith setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DAO_MemberWith setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public DAO_MemberWith setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public boolean isSex() {
        return sex;
    }

    public DAO_MemberWith setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public DAO_MemberWith setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    //---------------------------------------------------//

    public DAO_Resa getResa() {
        return resa;
    }

    public DAO_MemberWith setResa(DAO_Resa resa) {
        this.resa = resa;
        return this;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "DAO_MemberWith{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public MemberWith getMemberWith() {
                return new MemberWith(  this.id.toString(),
                                        this.name,
                                        this.surname,
                                        this.sex,
                                        this.birthday);
    }
}
