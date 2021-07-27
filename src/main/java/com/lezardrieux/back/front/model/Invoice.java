package com.lezardrieux.back.front.model;

import java.util.Date;
import java.util.List;

public class Invoice {

    private Long id;
    private String code;
    private String libelle;
    private int status;
    private Date created;
    List<InvoiceLine> lines;
    private double totalGross;
    private double totalVat;
    private double totalNet;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public Invoice() {}
    public Invoice(Long id, String code, String libelle, int status, Date created, List<InvoiceLine> lines, double totalGross, double totalVat, double totalNet) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.status = status;
        this.created = created;
        this.lines = lines;
        this.totalGross = totalGross;
        this.totalVat = totalVat;
        this.totalNet = totalNet;
    }
    //---------------------------------------------------//
    // GET
    //---------------------------------------------------//


    public Long getId() {
        return id;
    }

    public Invoice setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public Invoice setCreated(Date created) {
        this.created = created;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Invoice setCode(String code) {
        this.code = code;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Invoice setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Invoice setStatus(int status) {
        this.status = status;
        return this;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public Invoice setLines(List<InvoiceLine> lines) {
        this.lines = lines;
        return this;
    }

    public double getTotalGross() {
        return totalGross;
    }

    public Invoice setTotalGross(double totalGross) {
        this.totalGross = totalGross;
        return this;
    }

    public double getTotalVat() {
        return totalVat;
    }

    public Invoice setTotalVat(double totalVat) {
        this.totalVat = totalVat;
        return this;
    }

    public double getTotalNet() {
        return totalNet;
    }

    public Invoice setTotalNet(double totalNet) {
        this.totalNet = totalNet;
        return this;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
