package com.lezardrieux.back.front.model;


import java.util.Date;
import java.util.List;

public class Resa {

    private String id;
    private Date startDate;
    private Date endDate;
    private int days;

    private boolean identity;
    private String identityType;
    private String identityNum;
    private boolean animals;
    private boolean garage;
    private boolean van;
    private boolean car;
    private boolean elect;
    private String comment;

    private List<MemberWith> memberWiths;
    private List<Invoice> invoices;

    public Resa() {}
    public Resa(String id, Date startDate, Date endDate, int days, boolean identity, String identityType, String identityNum, boolean animals, boolean garage, boolean van, boolean car, boolean elect, String comment, List<MemberWith> memberWiths, List<Invoice> invoices) {
        this.id = id;
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
        this.memberWiths = memberWiths;
        this.invoices = invoices;
    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getDays() {
        return days;
    }

    public boolean isElect() {
        return elect;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public boolean isIdentity() {
        return identity;
    }

    public String getIdentityType() {
        return identityType;
    }

    public String getIdentityNum() {
        return identityNum;
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
                ", start=" + startDate +
                ", end=" + endDate +
                '}';
    }
}
