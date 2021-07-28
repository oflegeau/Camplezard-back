package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Place;
import com.lezardrieux.back.back.modelDAO.DAO_Resa;
import com.lezardrieux.back.back.modelDAO.DAO_TimeSlot;
import com.lezardrieux.back.back.repoDAO.Repo_TimeSlot;
import com.lezardrieux.back.front.model.Reponse;
import com.lezardrieux.back.front.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.lezardrieux.back.BackApplication.trace;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class BtF_TimeSlot implements DBS_TimeSlot {

    @Autowired
    Repo_TimeSlot repo_timeSlot;
    @Autowired
    DBS_Resa dbs_resa;
    @Autowired
    DBS_Place dbs_place;

    //------------------------------------------------------------------------------//

    private DAO_TimeSlot getBack(TimeSlot obj) {
        return new DAO_TimeSlot(obj.getDay(),
                                obj.getStatus());
    }

    //------------------------------------------------------------------------------//

    private TimeSlot getFront(DAO_TimeSlot obj) {
        return new TimeSlot(obj.getId(),
                            obj.getDay(),
                            obj.getStatus());
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public TimeSlot get(Long id) {
        Optional<DAO_TimeSlot> option = repo_timeSlot.findById(id);
        return option.map(this::getFront).orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_TimeSlot getDAO(Long id) {
        Optional<DAO_TimeSlot> option = repo_timeSlot.findById(id);
        return option.orElse(null);
    }

    //------------------------------------------------------------------------------//
    // POST
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_TimeSlot create(String idResa, String idPlace, TimeSlot obj) {
        try {
            // ----------------------------------------- //
            // sauvegarde sans jointure pour création ID //
            // ----------------------------------------- //
            DAO_TimeSlot dao_timeSlot = repo_timeSlot.save(getBack(obj));
            // ----------------------------------------- //
            // gestion des jointures                     //
            // ----------------------------------------- //
            DAO_Resa _parent = dbs_resa.getDAO(idResa);
            if (_parent == null) {
                LOGGER.error("DAO_Resa null/ID = " + idResa);
                return null;
            }
            dao_timeSlot = _parent.addTimeSlot(dao_timeSlot);
            // ----------------------------------------- //
            // gestion des jointures                     //
            // ----------------------------------------- //
            DAO_Place _parent1 = dbs_place.getDAO(idPlace);
            if (_parent1 == null) {
                LOGGER.error("DAO_Place null/ID = " + idResa);
                return null;
            }
            dao_timeSlot = _parent1.addSlot(dao_timeSlot);
            // ----------------------------------------- //
            dao_timeSlot = repo_timeSlot.save(dao_timeSlot);
            // ----------------------------------------- //
            if (trace) LOGGER.warn(dao_timeSlot.toString());
            return dao_timeSlot;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    //------------------------------------------------------------------------------//
    // DELETE
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse delete(Long id) {
        try {
            if (id.equals(0L)) {
                LOGGER.error("id null/ID = " + id.toString());
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_TimeSlot/delete/id null/ID = " + id.toString());
            }
            repo_timeSlot.deleteById(id);
            // ----------------------------------------- //
            if (trace) LOGGER.warn(id.toString());
            return new Reponse(HttpStatus.OK, "", id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_TimeSlot/delete " + e.getMessage());
        }
    }


    //------------------------------------------------------------------------------//

}
