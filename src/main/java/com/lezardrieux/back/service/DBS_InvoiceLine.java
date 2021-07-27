package com.lezardrieux.back.service;


import com.lezardrieux.back.back.modelDAO.DAO_InvoiceLine;
import com.lezardrieux.back.front.model.InvoiceLine;
import com.lezardrieux.back.front.model.Reponse;

import java.util.List;

public interface DBS_InvoiceLine {

    double getTotalGross();
    double getTotalNet();
    double getTotalVta();

    // Front Object //
    InvoiceLine get(Long id);

    // Back Object //
    DAO_InvoiceLine getDAO(Long id);

    // Front List of Object //
    List<InvoiceLine> getList(List<DAO_InvoiceLine> list);

    // create //
    boolean create(Long idInvoice, InvoiceLine obj);
    // update //
    Reponse update(InvoiceLine obj);
    // delete //
    Reponse delete(Long id);
}
