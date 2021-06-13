package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "connect_role")
public class DAO_ConnectRole {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role", length = 20, nullable = false)
    private String role;

    //---------------------------------------------------//
    // MANY TO ONE : N -> 1 (Listes)
    //---------------------------------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DAO_Connect connect;

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_ConnectRole() {
    }

    public DAO_ConnectRole(String role) {
        this.role = role;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //---------------------------------------------------//
    // ONE TO ONE  GETTER SETTER
    //---------------------------------------------------//

    public DAO_Connect getConnect() {
        return connect;
    }

    public DAO_ConnectRole setConnect(DAO_Connect connect) {
        this.connect = connect;
        return this;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "TRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
