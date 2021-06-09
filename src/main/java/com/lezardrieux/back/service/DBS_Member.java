package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Member;
import com.lezardrieux.back.front.model.*;

import java.util.List;
import java.util.UUID;

public interface DBS_Member {

    // Front Object //
    Member getMember_ById(String id);
    MemberPhoto getMemberPhoto_ById(String id);
    MemberCard getMemberCard_ById(String id);

    // Back Object //
    DAO_Member getBack(MemberCard obj);
    DAO_Member getMember_DAO_ById(String id);

    // Back List of Object //
    List<Member> getMember_List_ByJoin(List<DAO_Member> list);
    List<Member> getMember_List();
    List<MemberPhoto> getMemberPhoto_List();

    // Front List of Object //
    PageMember getMemberCard_Page(int page,
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
