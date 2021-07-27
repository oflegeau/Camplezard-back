package com.lezardrieux.back.front.model;


import java.util.Date;
import java.util.List;

public class Resa {

    private String id;
    private Date start;
    private Date end;

    private boolean identity;
    private boolean animals;
    private boolean garage;
    private boolean van;
    private boolean car;
    private String comment;

    private List<MemberWith> memberWiths;
    private List<TimeSlot> timeSlots;
    private List<Invoice> invoices;

    public Resa() {}
    public Resa(String id, Date start, Date end, boolean identity, boolean animals, boolean garage, boolean van, boolean car, String comment, List<MemberWith> memberWiths, List<TimeSlot> timeSlots, List<Invoice> invoices) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.identity = identity;
        this.animals = animals;
        this.garage = garage;
        this.van = van;
        this.car = car;
        this.comment = comment;
        this.memberWiths = memberWiths;
        this.timeSlots = timeSlots;
        this.invoices = invoices;
    }

    public String getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public boolean isIdentity() {
        return identity;
    }

    public boolean isAnimals() {
        return animals;
    }

    public boolean isCar() {
        return car;
    }

    public boolean isGarage() {
        return garage;
    }

    public boolean isVan() {
        return van;
    }

    public List<MemberWith> getMemberWiths() {
        return memberWiths;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Resa{" +
                "id='" + id + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
