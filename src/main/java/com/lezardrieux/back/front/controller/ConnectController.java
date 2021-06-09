package com.lezardrieux.back.front.controller;

import com.lezardrieux.back.front.model.Connect;
import com.lezardrieux.back.front.model.PageConnect;
import com.lezardrieux.back.front.model.Reponse;
import com.lezardrieux.back.service.DBS_Connect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@RestController
@RequestMapping(value = "/connect")
public class ConnectController {

    @Autowired
    DBS_Connect dbs_connect;

    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //
    //
    //            GET
    //
    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //

    @GetMapping
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<List<Connect>> getList() {

        LOGGER.info("GET............/connect");

        return ResponseEntity.status(HttpStatus.OK).body(dbs_connect.getConnect_List_ByAll());
    }

    @GetMapping(value = "/page")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<PageConnect> getPage(@RequestParam("page") int page,
                                               @RequestParam("size") int size,
                                               @RequestParam("filter") int filter,
                                               @RequestParam("sortAsc") boolean sortAsc,
                                               @RequestParam("sortName") String sortName) {

        LOGGER.info("GET............/connect/page");

        var entity = dbs_connect.getConnect_Page_ByAll(page, size, filter, sortAsc, sortName);
        if (entity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @GetMapping(value = "/last")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<Connect> updateLast() {
        try {
            LOGGER.info("GET............/connect/last");

            var entity = dbs_connect.update_GetLast();
            if (entity == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

            return ResponseEntity.status(HttpStatus.OK).body(entity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //
    //
    //            POST
    //
    // ---------------------------------------------------------------------------------------------- //
    // ---------------------------------------------------------------------------------------------- //

    @PostMapping(value = "/create")
    public ResponseEntity<Reponse> create(@RequestBody(required = true) String idToken,
                                          @RequestParam("name") String name,
                                          @RequestParam("surname") String surname,
                                          @RequestParam("phone") String phone) {


        LOGGER.info("POST............/connect/create");

        var rep = dbs_connect.create(idToken, name, surname, phone);
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

    @PutMapping(value = "/update/role")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Connect> updateRole(@RequestBody(required = true) String idConnect,
                                              @RequestParam("role") int role) {

        LOGGER.info("PUT............/connect/update/role");

        var entity = dbs_connect.update_Role(idConnect, role);
        if (entity == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @PutMapping(value = "/update")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Connect> update(@RequestBody(required = true) Connect Obj) {

        LOGGER.info("PUT............/connect/update");

        var entity = dbs_connect.update(Obj);
        if (entity == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(entity);
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
    public ResponseEntity<Reponse> delete(@PathVariable String id) {

        LOGGER.info("DELETE............/connect/delete" + id);

        var rep = dbs_connect.delete(id);
        if (rep == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(rep.getHttpStatus()).body(rep);
    }
}
