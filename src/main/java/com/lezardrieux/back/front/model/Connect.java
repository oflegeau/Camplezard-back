package com.lezardrieux.back.front.model;

import java.util.Date;

public class Connect {
    private String id;
    private int role;
    private String email;
    private String phone;
    private String name;
    private String surname;
    private boolean emailVerified;
    private Date created;
    private Date lastConnexion;
    private String idMember;
    private String avatar;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public Connect() { }
    public Connect(String id, int role, String email, String phone, String name, String surname, boolean emailVerified, Date created, Date lastConnexion, String idMember, String avatar) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.emailVerified = emailVerified;
        this.created = created;
        this.lastConnexion = lastConnexion;
        this.idMember = idMember;
        this.avatar = avatar;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public String getAvatar() {
        return avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIdMember() {
        return idMember;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "Connect{" +
                "id='" + id + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastConnexion=" + lastConnexion +
                ", idMember='" + idMember + '\'' +
                '}';
    }
}
