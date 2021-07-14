package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_TimeSlot;
import com.lezardrieux.back.front.model.TimeSlot;

public interface DBS_TimeSlot {

    DAO_TimeSlot getBack(TimeSlot obj);

    TimeSlot getTimeSlot_ById(Long id);

    DAO_TimeSlot getTimeSlot_DAO_ById(Long id);

}
