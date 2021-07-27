package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_MemberWith;
import com.lezardrieux.back.front.model.*;

import java.util.List;

public interface DBS_MemberWith {

    // Front Object //
    MemberWith get(String id);

    // Back Object //
    DAO_MemberWith getDAO(String id);

    // Back List of Object //
    List<MemberWith> getList(List<DAO_MemberWith> list);

    // create //
    Reponse create(String idResa, MemberWith obj);

    // update //
    Reponse update(MemberWith obj);

    // delete //
    Reponse delete(String id);
}
