package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_TimeSlot;
import com.lezardrieux.back.front.model.Reponse;
import com.lezardrieux.back.front.model.TimeSlot;

public interface DBS_TimeSlot {

    TimeSlot get(Long id);
    DAO_TimeSlot getDAO(Long id);

    DAO_TimeSlot create(String idResa, String idPlace, TimeSlot obj);

    Reponse delete(Long id);
}
