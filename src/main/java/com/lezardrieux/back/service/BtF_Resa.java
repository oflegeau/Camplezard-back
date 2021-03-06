package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.*;
import com.lezardrieux.back.back.repoDAO.Repo_Resa;
import com.lezardrieux.back.front.model.Reponse;
import com.lezardrieux.back.front.model.Resa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.lezardrieux.back.BackApplication.trace;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class BtF_Resa implements DBS_Resa {

    @Autowired
    Repo_Resa repo_resa;
    @Autowired
    DBS_Member dbs_member;
    @Autowired
    DBS_MemberWith dbs_memberWith;
    @Autowired
    DBS_TimeSlot dbs_timeSlot;
    @Autowired
    DBS_Invoice dbs_invoice;

    //------------------------------------------------------------------------------//

    private DAO_Resa getBack(Resa obj) {
        return new DAO_Resa(obj.getStartDate(),
                            obj.getEndDate(),
                            obj.getDays(),
                            obj.isIdentity(),
                            obj.getIdentityType().substring(0, Math.min(30, obj.getIdentityType().length())),
                            obj.getIdentityNum().substring(0, Math.min(30, obj.getIdentityNum().length())),
                            obj.isAnimals(),
                            obj.isGarage(),
                            obj.isVan(),
                            obj.isCar(),
                            obj.isElect(),
                            obj.getComment().substring(0, Math.min(255, obj.getComment().length())));
    }

    //------------------------------------------------------------------------------//

    private Resa getFront(DAO_Resa obj) {
        return new Resa(obj.getId().toString(),
                        obj.getStartDate(),
                        obj.getEndDate(),
                        obj.getDays(),
                        obj.isIdentity(),
                        obj.getIdentityType(),
                        obj.getIdentityNum(),
                        obj.isAnimals(),
                        obj.isGarage(),
                        obj.isVan(),
                        obj.isCar(),
                        obj.isElect(),
                        obj.getComment(),
                        dbs_memberWith.getList(obj.getMemberWiths()),
                        dbs_invoice.getList(obj.getInvoices()));
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Resa get(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Resa> option = repo_resa.findById(UUID.fromString(id));
        return option.map(this::getFront).orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_Resa getDAO(String id) {
        Optional<DAO_Resa> option = repo_resa.findById(UUID.fromString(id));
        return option.orElse(null);
    }

    @Override
    public List<Resa> getList(List<DAO_Resa> list) {
        List<Resa> _List = new ArrayList<>();
        for (DAO_Resa _TObj : list) {
            _List.add(getFront(_TObj));
        }
        return _List;
    }

    //------------------------------------------------------------------------------//
    // POST
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse create(String idMember, Resa obj) {
        try {
            // ----------------------------------------- //
            // sauvegarde sans jointure pour cr??ation ID //
            // ----------------------------------------- //
            DAO_Resa dao_resa = repo_resa.save(getBack(obj));
            // ----------------------------------------- //
            // gestion des jointures                     //
            // ----------------------------------------- //
            DAO_Member dao_member = dbs_member.getDAO(idMember);
            if (dao_member == null) {
                LOGGER.error("DAO_Member null/ID = " + obj.getId());
                return new Reponse(HttpStatus.NOT_FOUND, "Member null/ID = " + obj.getId());
            }
            dao_resa = dao_member.addResa(dao_resa);
            dao_resa = repo_resa.save(dao_resa);
            // ----------------------------------------- //
            if (trace) LOGGER.warn(dao_resa.toString());
            return new Reponse(HttpStatus.CREATED, dao_member.getId().toString(), 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Resa/create" + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
    // PUT
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse update(Resa obj) {
        try {
            // ----------------------------------------- //
            // r??cup??ration en base avant update         //
            // ----------------------------------------- //
            DAO_Resa dao_resa = getDAO(obj.getId());
            if (dao_resa == null) {
                LOGGER.error("DAO_Resa null/ID = " + obj.getId());
                return new Reponse(HttpStatus.NOT_FOUND, "DAO_Resa null/ID = " + obj.getId());
            }
            // ----------------------------------------- //
            // mise ?? jour                               //
            // ----------------------------------------- //
            dao_resa = dao_resa
                    .setStartDate(obj.getStartDate())
                    .setEndDate(obj.getEndDate())
                    .setIdentity(obj.isIdentity())
                    .setIdentityType(obj.getIdentityType().substring(0, Math.min(30, obj.getIdentityType().length())))
                    .setIdentityNum(obj.getIdentityNum().substring(0, Math.min(30, obj.getIdentityNum().length())))
                    .setAnimals(obj.isAnimals())
                    .setGarage(obj.isGarage())
                    .setVan(obj.isVan())
                    .setCar(obj.isCar())
                    .setElect(obj.isElect())
                    .setComment(obj.getComment().substring(0, Math.min(255, obj.getComment().length())));

            // ----------------------------------------- //
            repo_resa.save(dao_resa);

            if (trace) LOGGER.warn(dao_resa.toString());
            return new Reponse(HttpStatus.ACCEPTED, dao_resa.getId().toString(), 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Resa/update " + e.getMessage());
        }
    }
    //------------------------------------------------------------------------------//
    // DELETE
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public void deleteAll() {
        try {
            for (Iterator<DAO_Resa> iter = repo_resa.findAll().listIterator(); iter.hasNext(); ) {
                DAO_Resa dao_resa = iter.next();
                repo_resa.deleteById(dao_resa.getId());
                iter.remove();
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reponse delete(String id) {
        try {
            DAO_Resa dao_resa = getDAO(id);
            if (dao_resa == null) {
                LOGGER.error("DAO_Resa null/ID = " + id);
                return new Reponse(HttpStatus.NOT_FOUND, "DAO_Resa null/ID = " +id);
            }
            // ----------------------------------------- //
            repo_resa.deleteById(UUID.fromString(id));

            if (trace) LOGGER.warn(id);
            return new Reponse(HttpStatus.OK, id, 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Resa/delete " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reponse delete_Childs(String id) {
        try {
            // ----------------------------------------- //
            DAO_Resa dao_resa = getDAO(id);
            if (dao_resa == null) {
                LOGGER.error("DAO_Resa null/ID = " + id);
                return new Reponse(HttpStatus.NOT_FOUND, "DAO_Resa null/ID = " +id);
            }

            // ----------------------------------------- //

            for (Iterator<DAO_MemberWith> iter = dao_resa.getMemberWiths().listIterator(); iter.hasNext(); ) {
                DAO_MemberWith dao_memberWith = iter.next();

                // d??truit la jointure //
                dao_resa.detachMemberWith(dao_memberWith);
                // d??truit l'objet //
                dbs_memberWith.delete(dao_memberWith.getId().toString());

                iter.remove();
            }

            // ----------------------------------------- //

            for (Iterator<DAO_TimeSlot> iter = dao_resa.getTimeSlots().listIterator(); iter.hasNext(); ) {
                DAO_TimeSlot dao_timeSlot = iter.next();

                // d??truit la jointure //
                dao_resa.detachTimeSlot(dao_timeSlot);
                // d??truit l'objet //
                dbs_timeSlot.delete(dao_timeSlot.getId());

                iter.remove();
            }

            // ----------------------------------------- //

            for (Iterator<DAO_Invoice> iter = dao_resa.getInvoices().listIterator(); iter.hasNext(); ) {
                DAO_Invoice dao_invoice = iter.next();

                // d??truit la jointure //
                dao_resa.detachInvoice(dao_invoice);
                // d??truit l'objet //
                dbs_invoice.delete(dao_invoice.getId());

                iter.remove();
            }

            if (trace) LOGGER.warn(id);
            return new Reponse(HttpStatus.OK, "", 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "delete " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------------------- //
}

