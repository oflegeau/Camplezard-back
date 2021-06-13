package com.lezardrieux.back.front.controller;

import com.lezardrieux.back.front.model.*;
import com.lezardrieux.back.service.DBS_Connect;
import com.lezardrieux.back.service.DBS_Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    DBS_Member dbs_member;
    @Autowired
    DBS_Connect dbs_connect;

    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //
    //
    //            GET
    //
    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //

    // MemberList //
    @GetMapping
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<List<Member>> getMembers() {

        LOGGER.info("GET............/member");

        return ResponseEntity.status(HttpStatus.OK).body(dbs_member.getMember_List());
    }

    // MemberList //
    @GetMapping(value = "/photo")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<List<MemberPhoto>> getMemberPhotos() {

        LOGGER.info("GET............/member/photo");

        return ResponseEntity.status(HttpStatus.OK).body(dbs_member.getMemberPhoto_List());
    }

    // MemberCard //
    @GetMapping(value = "/page")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<PageMember> getPageMember(@RequestParam("page") int page,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("typeNation") int typeNation,
                                                    @RequestParam("sortAsc") boolean sortAsc,
                                                    @RequestParam("sortName") String sortName) {
        LOGGER.info("GET............/member/page");

        var entity = dbs_member.getMemberCard_Page(page, size, typeNation, sortAsc, sortName);
        if (entity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //
    //
    //            POST
    //
    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //

    @PostMapping(value = "/create")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Reponse> createMember(@RequestBody(required = true) MemberCard obj) {

        LOGGER.info("POST............/member/create");

        var rep = dbs_member.create(obj);
        if (rep == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(rep.getHttpStatus()).body(rep);
    }

    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //
    //
    //            PUT
    //
    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //

    @PutMapping(value = "/update")
    @PreAuthorize(value = "hasRole('MANAGER')")
    public ResponseEntity<Reponse> updateMember(@RequestBody(required = true) MemberCard obj) {

        LOGGER.info("PUT............/member/update");

        var rep = dbs_member.update(obj);
        if (rep == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(rep.getHttpStatus()).body(rep);
    }

    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //
    //
    //            DELETE
    //
    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Reponse> deleteMember(@PathVariable String id) {

        LOGGER.info("DELETE............/member/delete/" + id);

        var rep = dbs_member.delete(id);
        if (rep == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(rep.getHttpStatus()).body(rep);
    }

    @DeleteMapping(value = "/delete/connect/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Reponse> deleteConnect(@PathVariable String id) {

        LOGGER.info("DELETE............/member/delete/connect/" + id);

        var rep = dbs_connect.delete(id);
        if (rep == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(rep.getHttpStatus()).body(rep);
    }
}
