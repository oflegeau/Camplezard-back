package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lezardrieux.back.front.model.InvoiceLine;

import javax.persistence.*;

@Entity
@Table(name = "invoice_line")
public class DAO_InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "design", length = 30, nullable = false)         // #23 : recadrage de fonction ....
    private String design;
    @Column(name = "libelle", length = 255, nullable = false)       // Complexité simple (1 h/j) * Ratio (1.4)
    private String libelle;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "price")                                         // prix unitaire
    private double price;
    @Column(name = "vat")                                           // avec TVA ou pas
    private double vat;

    //---------------------------------------------------//
    // MANY TO ONE
    //---------------------------------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    DAO_Invoice invoice;

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_InvoiceLine() {}

    public DAO_InvoiceLine(String design, String libelle, double quantity, double price, double vat) {
        this.design = design;
        this.libelle = libelle;
        this.quantity = quantity;
        this.price = price;
        this.vat = vat;
    }
    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public Long getId() {
        return id;
    }
    public DAO_InvoiceLine setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDesign() {
        return design;
    }
    public DAO_InvoiceLine setDesign(String design) {
        this.design = design;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }
    public DAO_InvoiceLine setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public double getQuantity() {
        return quantity;
    }
    public DAO_InvoiceLine setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getPrice() {
        return price;
    }
    public DAO_InvoiceLine setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getVat() {
        return vat;
    }

    public DAO_InvoiceLine setVat(double vat) {
        this.vat = vat;
        return this;
    }

    //---------------------------------------------------//
    // MANY TO ONE  GETTER SETTER
    //---------------------------------------------------//

    public DAO_Invoice getInvoice() {
        return invoice;
    }
    public DAO_InvoiceLine setInvoice(DAO_Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "DAO_InvoiceLine{" +
                "id=" + id +
                ", design='" + design + '\'' +
                '}';
    }

    //---------------------------------------------------//
    // getFront
    //---------------------------------------------------//

    public InvoiceLine getInvoiceLine() {
        return new InvoiceLine(  this.getId(),
                                        this.getDesign(),
                                        this.getLibelle(),
                                        this.getQuantity(),
                                        this.getPrice(),
                                        this.getVat());
    }
}
