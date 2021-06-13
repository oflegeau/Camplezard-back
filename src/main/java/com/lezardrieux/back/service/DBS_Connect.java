package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Connect;
import com.lezardrieux.back.front.model.Connect;
import com.lezardrieux.back.front.model.PageConnect;
import com.lezardrieux.back.front.model.Reponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface DBS_Connect {


    // Front Object //
    Connect getConnect_ById(String id);
    Connect getConnect_ByIdFront(String idFront);

    // Back Object //
    DAO_Connect getBack(Connect obj);
    DAO_Connect getConnect_DAO_ById(String id);
    DAO_Connect getConnect_DAO_ByIdFront(String idFront);

    // Front List of Object //
    List<Connect> getConnect_List_ByAll();
    PageConnect getConnect_Page_ByAll(int page,
                                      int size,
                                      int filter,
                                      boolean sortAsc,
                                      String sortName);

    // create //
    Reponse create(String token, String name, String surname, String phone) throws UsernameNotFoundException;

   // update//
    Connect update(Connect obj);
    Connect update_GetLast() throws UsernameNotFoundException;
    Connect update_Role(String id, int newRole);
    Connect update_RolePost(DAO_Connect dao_connect, int roleAdd, int roleDel);

    // delate //
    Reponse delete(String idFront);
}
