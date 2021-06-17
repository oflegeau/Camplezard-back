package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lezardrieux.back.front.model.Connect;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "connect")
public class DAO_Connect {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "idFront", length = 38)                         // cqPHSVLUxBUmNWrATF3LkTyRntu2
    private String idFront;
    @Column(name = "idMember")                                       // "00000000-0000-0000-0000-000000000000" si pas de member
    private UUID idMember;
    @Column(name = "role")
    private int role;
    @Column(name = "email_verified", length = 1, nullable = false)  // email test Firebase
    private boolean emailVerified;
    @Column(name = "last_connexion")                                  // last_connexion Firebase
    private Date lastConnexion;

    @Column(name = "email", length = 128, nullable = false)         // email Firebase
    private String email;
    @Column(name = "phone", length = 14)                             // phone Firebase
    private String phone;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "surname", length = 20)
    private String surname;
    @Column(name = "created")                                         // created Firebase
    private Date created;
    @Column(name = "photo", columnDefinition = "TEXT")
    private String photo;
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
    @OneToMany( mappedBy = "connect",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_ConnectRole> roles = new ArrayList<>();


    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_Connect() {
    }

    public DAO_Connect(String idFront, UUID idMember, int role, boolean emailVerified, Date lastConnexion, String email, String phone, String name, String surname, Date created, String photo, int nation, Date birthday, boolean sex, String address, String code, String city) {
        this.idFront = idFront;
        this.idMember = idMember;
        this.role = role;
        this.emailVerified = emailVerified;
        this.lastConnexion = lastConnexion;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.created = created;
        this.photo = photo;
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


    public UUID getId() {
        return id;
    }

    public String getSId() {
        return id.toString();
    }

    public String getIdFront() {
        return idFront;
    }

    public UUID getIdMember() {
        return idMember;
    }

    public String getSIdMember() {
        return idMember.toString();
    }

    public String getPhoto() {
        return photo;
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

    public int getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastConnexion() {
        return lastConnexion;
    }

    //---------------------------------------------------//

    public DAO_Connect setRole(int role) {
        this.role = role;
        return this;
    }

    public DAO_Connect setEmail(String email) {
        this.email = email;
        return this;
    }

    public DAO_Connect setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public DAO_Connect setName(String name) {
        this.name = name;
        return this;
    }

    public DAO_Connect setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public DAO_Connect setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }

    public DAO_Connect setCreated(Date created) {
        this.created = created;
        return this;
    }

    public DAO_Connect setLastConnexion(Date lastConnexion) {
        this.lastConnexion = lastConnexion;
        return this;
    }

    public DAO_Connect setIdMember(UUID idMember) {
        this.idMember = idMember;
        return this;
    }

    public DAO_Connect setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public DAO_Connect setNation(int nation) {
        this.nation = nation;
        return this;
    }

    public DAO_Connect setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public DAO_Connect setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public DAO_Connect setAddress(String address) {
        this.address = address;
        return this;
    }

    public DAO_Connect setCode(String code) {
        this.code = code;
        return this;
    }

    public DAO_Connect setCity(String city) {
        this.city = city;
        return this;
    }

    //---------------------------------------------------//
    // ONE TO MANY  GETTER SETTER
    //---------------------------------------------------//

    public List<DAO_ConnectRole> getRoles() {
        return roles;
    }

    public DAO_ConnectRole addRole(DAO_ConnectRole Tobj) {
        roles.add(Tobj);
        Tobj.setConnect(this);
        return Tobj;
    }
    public DAO_ConnectRole detachRole(DAO_ConnectRole Tobj) {
        Tobj.setConnect(null);
        return Tobj;
    }
    public DAO_ConnectRole removeRole(DAO_ConnectRole Tobj) {
        roles.remove(Tobj);
        Tobj.setConnect(null);
        return Tobj;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "TConnect{" +
                "id='" + id.toString() + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", lastConnexion=" + lastConnexion +
                '}';
    }

    //---------------------------------------------------//
    // getFront
    //---------------------------------------------------//

    public Connect getConnect() {

        int role = 0;
        if ((this.getRole() & 0x01) == 0x01) role = 1;    // user
        if ((this.getRole() & 0x02) == 0x02) role = 2;    // user +  customer
        if ((this.getRole() & 0x04) == 0x04) role = 3;    // user +  customer + manager
        if ((this.getRole() & 0x08) == 0x08) role = 4;    // user +  customer + manager + admin

        return new Connect(this.getIdFront(),
                            role,
                            this.isEmailVerified(),
                            this.getLastConnexion(),
                            this.getEmail(),
                            this.getPhone(),
                            this.getName(),
                            this.getSurname(),
                            this.getCreated(),
                            this.getPhoto(),
                            this.getNation(),
                            this.getBirthday(),
                            this.isSex(),
                            this.getAddress(),
                            this.getCode(),
                            this.getCity());
    }
}
