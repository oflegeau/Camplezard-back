package com.lezardrieux.back.back.modelDAO;

import com.lezardrieux.back.front.model.Connect;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "connect")
public class DAO_Connect {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "role")
    private int role;
    @Column(name = "email", length = 128, nullable = false)         // email Firebase
    private String email;
    @Column(name = "phone", length = 14)                             // phone Firebase
    private String phone;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "surname", length = 20)
    private String surname;
    @Column(name = "email_verified", length = 1, nullable = false)  // email test Firebase
    private boolean emailVerified;
    @Column(name = "created")                                         // created Firebase
    private Date created;
    @Column(name = "last_connexion")                                  // last_connexion Firebase
    private Date lastConnexion;

    //---------------------------------------------------//
    // ONE TO ONE : 1 -> 1
    //---------------------------------------------------//

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinTable(name = "connect_member",
            joinColumns =
                    {@JoinColumn(name = "connect_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "member_id", referencedColumnName = "id")})
    private DAO_Member member;

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_Connect() {
    }

    public DAO_Connect(int role, String email, String phone, String name, String surname, boolean emailVerified, Date created, Date lastConnexion) {
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.emailVerified = emailVerified;
        this.created = created;
        this.lastConnexion = lastConnexion;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public UUID getId() { return id; }
    public String getSId() { return id.toString(); }

    public DAO_Connect setId(UUID id) {
        this.id = id;
        return this;
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

    //---------------------------------------------------//
    // ONE TO MANY  GETTER SETTER
    //---------------------------------------------------//

    public DAO_Member getMember() {
        return member;
    }

    public void setMember(DAO_Member member) {
        this.member = member;
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

        String idMember = "";
        String avatar = "";
        if (this.getMember() != null) {
            idMember = this.getMember().getSId();
            avatar = this.getMember().getPhoto();
        }

        int role = 0;
        if ((this.getRole() & 0x01) == 0x01) role = 1;    // user
        if ((this.getRole() & 0x02) == 0x02) role = 2;    // user +  manager
        if ((this.getRole() & 0x04) == 0x04) role = 3;    // user +  manager + admin

        return new Connect(this.getId().toString(),
                            role,
                            this.getEmail(),
                            this.getPhone(),
                            this.getName(),
                            this.getSurname(),
                            this.isEmailVerified(),
                            this.getCreated(),
                            this.getLastConnexion(),
                            idMember,
                            avatar);
    }


}
