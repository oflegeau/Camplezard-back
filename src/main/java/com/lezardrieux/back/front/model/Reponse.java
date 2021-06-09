package com.lezardrieux.back.front.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class Reponse {
    @JsonIgnore
    HttpStatus httpStatus;

    boolean ok;
    String message;
    int code;                               // 0x01 : Assignations
                                            // 0x02 : Facturation
                                            // 0x10 : error : contrat should be 0
                                            // 0x20 : error : contrat should be > 0
    String ids;
    Long idn;

    public Reponse() {
    }

    public Reponse(HttpStatus httpStatus, String message) {
        this.ok = false;
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = 0;
        this.ids = "";
        this.idn = 0L;
    }

    public Reponse(HttpStatus httpStatus, String ids, Long idn) {
        this.ok = true;
        this.httpStatus = httpStatus;
        this.message = "";
        this.code = 0;
        this.ids = ids;
        this.idn = idn;
    }

    public Reponse(HttpStatus httpStatus, boolean ok, String message, int code, String ids, Long idn) {
        this.httpStatus = httpStatus;
        this.ok = ok;
        this.message = message;
        this.code = code;
        this.ids = ids;
        this.idn = idn;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Reponse setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Reponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isOk() {
        return ok;
    }

    public Reponse setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public Long getIdn() {
        return idn;
    }

    public Reponse setIdn(Long idn) {
        this.idn = idn;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Reponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getIds() {
        return ids;
    }

    public Reponse setIds(String ids) {
        this.ids = ids;
        return this;
    }
}
