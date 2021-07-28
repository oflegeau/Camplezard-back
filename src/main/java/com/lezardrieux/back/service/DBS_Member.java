package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Member;
import com.lezardrieux.back.front.model.*;

import java.util.List;
import java.util.UUID;

public interface DBS_Member {

    // Front Object //
    Member get(String id);
    MemberPhoto get_Photo(String id);
    MemberCard get_Card(String id);
    MemberResa get_Resa(String id);

    // Back Object //
    DAO_Member getDAO(String id);

    // Back List of Object //
    List<Member> getList(List<DAO_Member> list);
    List<Member> getList();
    List<MemberPhoto> getList_Photo();

    // Front List of Object //
    PageMember getPage_Card(int page,
                              int size,
                              int typeNation,
                              boolean sortAsc,
                              String sortName);

    // create //
    Reponse create(MemberCard obj);

    // update //
    Reponse update(MemberCard obj);

    // delete //
    void deleteAll();
    Reponse delete(String id);
}
