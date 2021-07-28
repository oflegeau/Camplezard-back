package com.lezardrieux.back.front.controller;

import com.lezardrieux.back.front.model.PagePlace;
import com.lezardrieux.back.front.model.Place;
import com.lezardrieux.back.front.model.PlaceSlot;
import com.lezardrieux.back.service.DBS_Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@RestController
@RequestMapping(value = "/place")
public class PlaceController {

    @Autowired
    DBS_Place dbs_place;

    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //
    //
    //            GET
    //
    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //

    // Place //
    @GetMapping(value = "/list")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<List<Place>> getList() {

        LOGGER.info("GET............/place/page");

        return ResponseEntity.status(HttpStatus.OK).body(dbs_place.getList());
    }

    // PagePlace //
    @GetMapping(value = "/page")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<PagePlace> getPage(@RequestParam("zone") int zone) {

        LOGGER.info("GET............/place/page");

        return ResponseEntity.status(HttpStatus.OK).body(dbs_place.getPage(zone));
    }

    // MemberData //
    @GetMapping(value = "/{id}/slot")
    @PreAuthorize(value = "hasRole('MANAGER')")
    public ResponseEntity<PlaceSlot> getMemberData(@PathVariable String id,
                                                    @RequestParam("keyMonthMin") String keyMonthMin,
                                                    @RequestParam("keyMonthMax") String keyMonthMax) {

        LOGGER.info("GET............/place/" + id + "/slot - keyMonthMin=" + keyMonthMin + " keyMonthMax=" + keyMonthMax);

        var entity = dbs_place.get(id, keyMonthMin,  keyMonthMax);
        if (entity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }
}
