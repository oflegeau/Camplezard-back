package com.lezardrieux.back.service.back_to_front;

import com.lezardrieux.back.back.modelDAO.DAO_TimeSlot;
import com.lezardrieux.back.back.repoDAO.Repo_TimeSlot;
import com.lezardrieux.back.front.model.TimeSlot;
import com.lezardrieux.back.service.DBS_TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BtF_TimeSlot implements DBS_TimeSlot {

    @Autowired
    Repo_TimeSlot repo_timeSlot;

    //------------------------------------------------------------------------------//

    @Override
    public DAO_TimeSlot getBack(TimeSlot obj) {
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
    public TimeSlot getTimeSlot_ById(Long id) {
        if (id.equals("")) return null;
        Optional<DAO_TimeSlot> option = repo_timeSlot.findById(id);
        return option.map(this::getFront).orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_TimeSlot getTimeSlot_DAO_ById(Long id) {
        if (id.equals("")) return null;
        Optional<DAO_TimeSlot> option = repo_timeSlot.findById(id);
        return option.orElse(null);
    }

    //------------------------------------------------------------------------------//

}
