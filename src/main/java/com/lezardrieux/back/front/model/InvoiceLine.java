package com.lezardrieux.back.front.model;

public class InvoiceLine {

    private Long id;
    private String design;
    private String libelle;
    private double quantity;
    private double price;
    private double vat;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public InvoiceLine() {}
    public InvoiceLine(Long id, String design, String libelle, double quantity, double price, double vat) {
        this.id = id;
        this.design = design;
        this.libelle = libelle;
        this.quantity = quantity;
        this.price = price;
        this.vat = vat;
    }
    //---------------------------------------------------//
    // GET
    //---------------------------------------------------//

    public Long getId() {
        return id;
    }

    public String getDesign() {
        return design;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getVat() {
        return vat;
    }

    public InvoiceLine setId(Long id) {
        this.id = id;
        return this;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "id=" + id +
                ", design='" + design + '\'' +
                '}';
    }
}
