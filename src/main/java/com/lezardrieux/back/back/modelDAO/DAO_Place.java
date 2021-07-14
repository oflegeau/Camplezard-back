package com.lezardrieux.back.back.modelDAO;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "place")
public class DAO_Place {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "code", length = 10, nullable = false)
    private String code;
    @Column(name = "type")
    private int type;                           // 1 : 2 pers.
                                                // 2 : 2-4 pers.
                                                // 3 : 5-6 pers.
                                                // 4 : Acceuil
                                                // 5 : Sanitaire
                                                // 6 : douche
                                                // 7 : lingerie
                                                // 8 : commerce
                                                // 9 : Loisir
    @Column(name = "comment", length = 64, nullable = false)
    private String comment;
    @Column(name = "line")
    private int line;
    @Column(name = "zone")
    private int zone;
    @Column(name = "status")
    private int status;
    @Column(name = "famous")
    private int famous;
    @Column(name = "avis")
    private int avis;
    @Column(name = "price")
    private double price;
    @Column(name = "photo", columnDefinition = "TEXT")
    private String photo;
    @Column(name = "video", columnDefinition = "TEXT")
    private String video;
    @Column(name = "van")
    private boolean van;
    @Column(name = "water")
    private boolean water;
    @Column(name = "elect")
    private boolean elect;

    //---------------------------------------------------//
    // ONE TO ONE : 1 -> 1
    //---------------------------------------------------//
    @OneToMany( mappedBy = "place",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_TimeSlot> slots = new ArrayList<>();

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_Place() {}

    public DAO_Place(String code, int type, String comment, int line, int zone, int status, int famous, int avis, double price, String photo, String video, boolean van, boolean water, boolean elect) {
        this.code = code;
        this.type = type;
        this.comment = comment;
        this.line = line;
        this.zone = zone;
        this.status = status;
        this.famous = famous;
        this.avis = avis;
        this.price = price;
        this.photo = photo;
        this.video = video;
        this.van = van;
        this.water = water;
        this.elect = elect;
    }

    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public UUID getId() {
        return id;
    }

    public DAO_Place setId(UUID id) {
        this.id = id;
        return this;
    }

    public int getLine() {
        return line;
    }

    public DAO_Place setLine(int line) {
        this.line = line;
        return this;
    }

    public int getZone() {
        return zone;
    }

    public DAO_Place setZone(int zone) {
        this.zone = zone;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public DAO_Place setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public DAO_Place setStatus(int status) {
        this.status = status;
        return this;
    }

    public int getFamous() {
        return famous;
    }

    public DAO_Place setFamous(int famous) {
        this.famous = famous;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public DAO_Place setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public DAO_Place setVideo(String video) {
        this.video = video;
        return this;
    }

    public int getType() {
        return type;
    }

    public DAO_Place setType(int type) {
        this.type = type;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public DAO_Place setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DAO_Place setCode(String code) {
        this.code = code;
        return this;
    }

    public int getAvis() {
        return avis;
    }

    public DAO_Place setAvis(int avis) {
        this.avis = avis;
        return this;
    }

    public boolean isVan() {
        return van;
    }

    public DAO_Place setVan(boolean van) {
        this.van = van;
        return this;
    }

    public boolean isWater() {
        return water;
    }

    public DAO_Place setWater(boolean water) {
        this.water = water;
        return this;
    }

    public boolean isElect() {
        return elect;
    }

    public DAO_Place setElect(boolean elect) {
        this.elect = elect;
        return this;
    }

    //---------------------------------------------------//

    public List<DAO_TimeSlot> getSlots() {
        return slots;
    }

    public DAO_Place setSlots(List<DAO_TimeSlot> slots) {
        this.slots = slots;
        return this;
    }

    public DAO_TimeSlot addSlot(DAO_TimeSlot Tobj) {
        slots.add(Tobj);
        Tobj.setPlace(this);
        return Tobj;
    }
    public DAO_TimeSlot detachSlot(DAO_TimeSlot Tobj) {
        Tobj.setPlace(null);
        return Tobj;
    }
    public DAO_TimeSlot removeSlot(DAO_TimeSlot Tobj) {
        slots.remove(Tobj);
        Tobj.setPlace(null);
        return Tobj;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "DAO_Place{" +
                "id=" + id +
                '}';
    }

}
