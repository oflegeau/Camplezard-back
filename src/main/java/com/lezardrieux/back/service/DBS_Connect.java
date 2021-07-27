package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Connect;
import com.lezardrieux.back.front.model.Connect;
import com.lezardrieux.back.front.model.Reponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface DBS_Connect {

    // Front Object //
    Connect get(String id);
    Connect get_ByIdFront(String idFront);

    // Back Object //
    DAO_Connect getBack(Connect obj);
    DAO_Connect getDAO(String id);
    DAO_Connect getDAO_ByIdFront(String idFront);

    // Front List of Object //
    List<Connect> getList();
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
