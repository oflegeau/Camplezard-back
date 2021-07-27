package com.lezardrieux.back.front.controller;

import com.lezardrieux.back.front.model.Member;
import com.lezardrieux.back.front.model.PagePlace;
import com.lezardrieux.back.service.DBS_Connect;
import com.lezardrieux.back.service.DBS_Member;
import com.lezardrieux.back.service.DBS_Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    // PagePlace //
    @GetMapping(value = "/page")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<PagePlace> getPage(@RequestParam("zone") int zone) {

        LOGGER.info("GET............/place/page");

        return ResponseEntity.status(HttpStatus.OK).body(dbs_place.getPage(zone));
    }
}
