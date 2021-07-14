package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "timeslot")
public class DAO_TimeSlot {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "day")
    private Date day;
    @Column(name = "status")
    private int status;

    //---------------------------------------------------//
    // MANY TO ONE : N -> 1 (Listes)
    //---------------------------------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DAO_Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DAO_Member member;

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_TimeSlot() {}
    public DAO_TimeSlot(Date day, int status) {
        this.day = day;
        this.status = status;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public Long getId() {
        return id;
    }

    public DAO_TimeSlot setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDay() {
        return day;
    }

    public DAO_TimeSlot setDay(Date day) {
        this.day = day;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public DAO_TimeSlot setStatus(int status) {
        this.status = status;
        return this;
    }

    public DAO_Place getPlace() {
        return place;
    }

    public DAO_TimeSlot setPlace(DAO_Place place) {
        this.place = place;
        return this;
    }

    public DAO_Member getMember() {
        return member;
    }

    public DAO_TimeSlot setMember(DAO_Member member) {
        this.member = member;
        return this;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//


    @Override
    public String toString() {
        return "DAO_TimeSlot{" +
                "id=" + id +
                ", day=" + day +
                '}';
    }
}
