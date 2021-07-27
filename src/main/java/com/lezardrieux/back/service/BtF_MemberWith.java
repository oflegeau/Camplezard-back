package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_MemberWith;
import com.lezardrieux.back.back.modelDAO.DAO_Resa;
import com.lezardrieux.back.back.repoDAO.Repo_MemberWith;
import com.lezardrieux.back.front.model.MemberWith;
import com.lezardrieux.back.front.model.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.lezardrieux.back.BackApplication.trace;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
class BtF_MemberWith implements DBS_MemberWith {

    @Autowired
    Repo_MemberWith repo_memberWith;
    @Autowired
    DBS_Resa dbs_resa;

    private DAO_MemberWith getBack(MemberWith obj) {
        return new DAO_MemberWith(     obj.getName().substring(0, Math.min(20, obj.getName().length())),
                obj.getSurname().substring(0, Math.min(20, obj.getSurname().length())),
                obj.isSex(),
                obj.getBirthday());
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public MemberWith get(String id) {
        if (id.equals("")) return null;
        Optional<DAO_MemberWith> option = repo_memberWith.findById(UUID.fromString(id));
        return option.map(DAO_MemberWith::getMemberWith).orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_MemberWith getDAO(String id) {
        if (id.equals("")) return null;
        Optional<DAO_MemberWith> option = repo_memberWith.findById(UUID.fromString(id));
        return option.orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    public List<MemberWith> getList(List<DAO_MemberWith> list) {
        List<MemberWith> _List = new ArrayList<>();
        for (DAO_MemberWith _TObj : list) {
            _List.add(_TObj.getMemberWith());
        }
        return _List;
    }

    //------------------------------------------------------------------------------//
    // POST
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse create(String idResa, MemberWith obj) {
        try {
            // ----------------------------------------- //
            // sauvegarde sans jointure pour création ID //
            // ----------------------------------------- //
            DAO_MemberWith dao_memberWith = repo_memberWith.save(getBack(obj));
            // ----------------------------------------- //
            // gestion des jointures                     //
            // ----------------------------------------- //
            DAO_Resa _parent = dbs_resa.getDAO(idResa);
            if (_parent == null) {
                LOGGER.error("DAO_Resa null/ID = " + idResa);
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_MemberWith/create/DAO_Resa null/ID = " + idResa);
            }
            dao_memberWith = _parent.addMemberWith(dao_memberWith);
            // ----------------------------------------- //
            repo_memberWith.save(dao_memberWith);

            // ----------------------------------------- //
            if (trace) LOGGER.warn(dao_memberWith.toString());
            return new Reponse(HttpStatus.CREATED, dao_memberWith.getId().toString(), 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_MemberWith/create" + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
    // PUT
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse update(MemberWith obj) {
        try {
            // ----------------------------------------- //
            // récupération en base avant update         //
            // ----------------------------------------- //
            DAO_MemberWith dao_memberWith = getDAO(obj.getId());
            if (dao_memberWith == null) {
                LOGGER.error("DAO_MemberWith null/ID = " + obj.getId());
                return new Reponse(HttpStatus.NOT_FOUND, "DAO_MemberWith null/ID = " + obj.getId());
            }
            // ----------------------------------------- //
            // mise à jour                               //
            // ----------------------------------------- //
            dao_memberWith = dao_memberWith
                    .setName(obj.getName().substring(0, Math.min(20, obj.getName().length())))
                    .setSurname(obj.getSurname().substring(0, Math.min(20, obj.getSurname().length())))
                    .setBirthday(obj.getBirthday())
                    .setSex(obj.isSex());

            // ----------------------------------------- //
            repo_memberWith.save(dao_memberWith);

            if (trace) LOGGER.warn(dao_memberWith.toString());
            return new Reponse(HttpStatus.ACCEPTED, dao_memberWith.getId().toString(), 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_MemberWith/update " + e.getMessage());
        }
    }


    //------------------------------------------------------------------------------//
    // DELETE
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse delete(String id) {
        try {
            DAO_MemberWith dao_memberWith = getDAO(id);
            if (dao_memberWith == null) {
                LOGGER.error("DAO_MemberWith null/ID = " + id);
                return new Reponse(HttpStatus.NOT_FOUND, "DAO_MemberWith null/ID = " + id);
            }
            // ----------------------------------------- //
            repo_memberWith.deleteById(UUID.fromString(id));

            if (trace) LOGGER.warn(id);
            return new Reponse(HttpStatus.OK, id, 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_MemberWith/delete " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------------------- //
}
