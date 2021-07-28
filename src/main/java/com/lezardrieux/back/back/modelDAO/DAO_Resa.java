package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "resa")
public class DAO_Resa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date endDate;
    @Column(name = "days")
    private int days;

    @Column(name = "identity")
    private boolean identity;
    @Column(name = "identity_type", length = 30)
    private String identityType;
    @Column(name = "identity_num", length = 30)
    private String identityNum;

    @Column(name = "animals")
    private boolean animals;
    @Column(name = "garage")
    private boolean garage;
    @Column(name = "van")
    private boolean van;
    @Column(name = "car")
    private boolean car;
    @Column(name = "elect")
    private boolean elect;
    @Column(name = "comment", length = 255)
    private String comment;

    //---------------------------------------------------//
    // ONE TO ONE : 1 -> 1
    //---------------------------------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DAO_Member member;

    //---------------------------------------------------//
    // ONE TO MANY : 1 -> n
    //---------------------------------------------------//

    @OneToMany( mappedBy = "resa",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_MemberWith> memberWiths = new ArrayList<>();

    @OneToMany( mappedBy = "resa",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_TimeSlot> timeSlots = new ArrayList<>();

    @OneToMany( mappedBy = "resa",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_Invoice> invoices = new ArrayList<>();

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_Resa() {}

    public DAO_Resa(Date startDate, Date endDate, int days, boolean identity, String identityType, String identityNum, boolean animals, boolean garage, boolean van, boolean car, boolean elect, String comment) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.identity = identity;
        this.identityType = identityType;
        this.identityNum = identityNum;
        this.animals = animals;
        this.garage = garage;
        this.van = van;
        this.car = car;
        this.elect = elect;
        this.comment = comment;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public UUID getId() {
        return id;
    }

    public DAO_Resa setId(UUID id) {
        this.id = id;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public DAO_Resa setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public DAO_Resa setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public boolean isIdentity() {
        return identity;
    }

    public DAO_Resa setIdentity(boolean identity) {
        this.identity = identity;
        return this;
    }

    public int getDays() {
        return days;
    }

    public DAO_Resa setDays(int days) {
        this.days = days;
        return this;
    }

    public boolean isElect() {
        return elect;
    }

    public DAO_Resa setElect(boolean elect) {
        this.elect = elect;
        return this;
    }

    public boolean isAnimals() {
        return animals;
    }

    public DAO_Resa setAnimals(boolean animals) {
        this.animals = animals;
        return this;
    }

    public boolean isGarage() {
        return garage;
    }

    public DAO_Resa setGarage(boolean garage) {
        this.garage = garage;
        return this;
    }

    public boolean isVan() {
        return van;
    }

    public DAO_Resa setVan(boolean van) {
        this.van = van;
        return this;
    }

    public boolean isCar() {
        return car;
    }

    public DAO_Resa setCar(boolean car) {
        this.car = car;
        return this;
    }

    public String getIdentityType() {
        return identityType;
    }

    public DAO_Resa setIdentityType(String identityType) {
        this.identityType = identityType;
        return this;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public DAO_Resa setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public DAO_Resa setComment(String comment) {
        this.comment = comment;
        return this;
    }

    //---------------------------------------------------//

    public DAO_Member getMember() {
        return member;
    }

    public DAO_Resa setMember(DAO_Member member) {
        this.member = member;
        return this;
    }

    //---------------------------------------------------//

    public List<DAO_MemberWith> getMemberWiths() {
        return memberWiths;
    }

    public DAO_Resa setMemberWiths(List<DAO_MemberWith> memberWiths) {
        this.memberWiths = memberWiths;
        return this;
    }

    public DAO_MemberWith addMemberWith(DAO_MemberWith Tobj) {
        memberWiths.add(Tobj);
        Tobj.setResa(this);
        return Tobj;
    }
    public DAO_MemberWith detachMemberWith(DAO_MemberWith Tobj) {
        Tobj.setResa(null);
        return Tobj;
    }
    public DAO_MemberWith removeMemberWith(DAO_MemberWith Tobj) {
        memberWiths.remove(Tobj);
        Tobj.setResa(null);
        return Tobj;
    }

    //---------------------------------------------------//

    public List<DAO_TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public DAO_Resa setTimeSlots(List<DAO_TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
        return this;
    }

    public DAO_TimeSlot addTimeSlot(DAO_TimeSlot Tobj) {
        timeSlots.add(Tobj);
        Tobj.setResa(this);
        return Tobj;
    }
    public DAO_TimeSlot detachTimeSlot(DAO_TimeSlot Tobj) {
        Tobj.setResa(null);
        return Tobj;
    }
    public DAO_TimeSlot removeTimeSlot(DAO_TimeSlot Tobj) {
        timeSlots.remove(Tobj);
        Tobj.setResa(null);
        return Tobj;
    }

    //---------------------------------------------------//

    public List<DAO_Invoice> getInvoices() {
        return invoices;
    }

    public DAO_Resa setInvoices(List<DAO_Invoice> slots) {
        this.invoices = slots;
        return this;
    }

    public DAO_Invoice addInvoice(DAO_Invoice Tobj) {
        invoices.add(Tobj);
        Tobj.setResa(this);
        return Tobj;
    }
    public DAO_Invoice detachInvoice(DAO_Invoice Tobj) {
        Tobj.setResa(null);
        return Tobj;
    }
    public DAO_Invoice removeInvoice(DAO_Invoice Tobj) {
        invoices.remove(Tobj);
        Tobj.setResa(null);
        return Tobj;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "DAO_Resa{" +
                "id=" + id +
                ", start=" + startDate +
                ", end=" + endDate +
                '}';
    }
}
