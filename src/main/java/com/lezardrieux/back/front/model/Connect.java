package com.lezardrieux.back.front.model;

import java.util.Date;

public class Connect {

    private String idFront;
    private int role;
    private boolean emailVerified;
    private Date lastConnexion;

    private String email;
    private String phone;
    private String name;
    private String surname;
    private Date created;
    private String photo;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public Connect() { }

    public Connect(String idFront, int role, boolean emailVerified, Date lastConnexion, String email, String phone, String name, String surname, Date created, String photo) {
        this.idFront = idFront;
        this.role = role;
        this.emailVerified = emailVerified;
        this.lastConnexion = lastConnexion;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.created = created;
        this.photo = photo;
    }
    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    public String getIdFront() {
        return idFront;
    }

    public String getPhoto() {
        return photo;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "Connect{" +
                "idFront='" + idFront + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastConnexion=" + lastConnexion +
                '}';
    }
}
