package com.lezardrieux.back.back.modelDAO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lezardrieux.back.front.model.Invoice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice")
public class DAO_Invoice {

    @Id
    @GeneratedValue
    private Long id;            // numéro chrono facture
    @Column(name = "code", length = 30, nullable = false)
    private String code;
    @Column(name = "libelle", length = 255, nullable = false)
    private String libelle;
    @Column(name = "status")
    private int status;         // 0 crée , 1 envoyée, 2 payée
    @Column(name = "created")
    private Date created;

    //---------------------------------------------------//
    // MANY TO ONE
    //---------------------------------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private DAO_Resa resa;

    //---------------------------------------------------//
    // ONR TO MANY
    //---------------------------------------------------//

    @OneToMany( mappedBy = "invoice",
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    @JsonManagedReference
    private List<DAO_InvoiceLine> lines = new ArrayList<>();

    //---------------------------------------------------//
    // CONSTRUCTOR without Jointures
    //---------------------------------------------------//

    public DAO_Invoice() { }
    public DAO_Invoice(String code, String libelle, int status, Date created) {
        this.code = code;
        this.libelle = libelle;
        this.status = status;
        this.created = created;
    }
    //---------------------------------------------------//
    // SIMPLE GETTER SETTER
    //---------------------------------------------------//

    public Long getId() {
        return id;
    }
    public DAO_Invoice setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DAO_Invoice setCode(String code) {
        this.code = code;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public DAO_Invoice setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public DAO_Invoice setStatus(int status) {
        this.status = status;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public DAO_Invoice setCreated(Date created) {
        this.created = created;
        return this;
    }

    //---------------------------------------------------//
    // MANY TO ONE  GETTER SETTER
    //---------------------------------------------------//

    public DAO_Resa getResa() {
        return resa;
    }

    public DAO_Invoice setResa(DAO_Resa resa) {
        this.resa = resa;
        return this;
    }

    //---------------------------------------------------//
    // ONE TO MANY  GETTER SETTER
    //---------------------------------------------------//

    public List<DAO_InvoiceLine> getLines() {
        return lines;
    }
    public DAO_InvoiceLine addLine(DAO_InvoiceLine Tobj) {
        lines.add(Tobj);
        Tobj.setInvoice(this);
        return Tobj;
    }
    public DAO_InvoiceLine detachLine(DAO_InvoiceLine Tobj) {
        Tobj.setInvoice(null);
        return Tobj;
    }
    public DAO_InvoiceLine removeLine(DAO_InvoiceLine Tobj) {
        lines.remove(Tobj);
        Tobj.setInvoice(null);
        return Tobj;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "DAO_Invoice{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", status=" + status +
                '}';
    }

    //---------------------------------------------------//
    // getFront
    //---------------------------------------------------//

    public Invoice getInvoice() {
        return new Invoice( this.getId(),
                            this.getCode(),
                            this.getLibelle(),
                            this.getStatus(),
                            this.getCreated(),
                            null,
                            0, 0, 0);
    }
}
