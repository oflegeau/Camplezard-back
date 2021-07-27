package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Invoice;
import com.lezardrieux.back.front.model.Invoice;
import com.lezardrieux.back.front.model.Reponse;

import java.util.List;

public interface DBS_Invoice {

    // Front Object //
    Invoice get(Long id);

    // Back Object //
    DAO_Invoice getDAO(Long id);

    // Front List of Object //
    List<Invoice> getList(List<DAO_Invoice> list);

    // create //
    Reponse create(String idResa, Invoice obj);

    // update //
    Reponse update(Invoice obj);

    // delete //
    Reponse delete(Long id);
    Reponse delete_Childs(Long id);
}
