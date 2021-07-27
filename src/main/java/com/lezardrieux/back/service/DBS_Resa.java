package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Resa;
import com.lezardrieux.back.front.model.Reponse;
import com.lezardrieux.back.front.model.Resa;

import java.util.List;

public interface DBS_Resa {

    // Front Object //
    Resa get(String id);

    // Back Object //
    DAO_Resa getDAO(String id);

    // Front List of Object //
    List<Resa> getList(List<DAO_Resa> list);

    // create //
    Reponse create(String idMember, Resa obj);

    // update //
    Reponse update(Resa obj);

    // delete //
    void deleteAll();
    Reponse delete(String id);
    Reponse delete_Childs(String id);
}
