package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "connect_access")
public class DAO_ConnectAccess {

    @Id
    @Column(name = "id", length = 28, nullable = false)        // id Firebase
    private String id;

    //---------------------------------------------------//
    // ONE TO ONE : 1 -> 1
    //---------------------------------------------------//

    @OneToOne
    private DAO_Connect connect;

    //---------------------------------------------------//
    // ONE TO MANY : 1 -> N (Listes)
    //---------------------------------------------------//

    @OneToMany(mappedBy = "access",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_ConnectRole> roles = new ArrayList<>();

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_ConnectAccess() {
    }

    public DAO_ConnectAccess(String id) {
        this.id = id;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //---------------------------------------------------//
    // ONE TO ONE  GETTER SETTER
    //---------------------------------------------------//

    public DAO_Connect getConnect() {
        return connect;
    }

    public DAO_ConnectAccess setConnect(DAO_Connect connect) {
        this.connect = connect;
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
        Tobj.setAccess(this);
        return Tobj;
    }
    public DAO_ConnectRole detachRole(DAO_ConnectRole Tobj) {
        Tobj.setAccess(null);
        return Tobj;
    }
    public DAO_ConnectRole removeRole(DAO_ConnectRole Tobj) {
        roles.remove(Tobj);
        Tobj.setAccess(null);
        return Tobj;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "TAccess{" +
                "id='" + id + '\'' +
                ", roles=" + roles +
                '}';
    }
}
