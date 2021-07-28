package com.lezardrieux.back.front.model;

import java.util.Date;
import java.util.List;

public class MemberResa extends MemberCard {

    private List<Resa> resas;

    public MemberResa() {}
    public MemberResa(String id, String name, String surname, String photo, Date created, String email, String phone, int nation, Date birthday, String birthdayCity, String profession, boolean sex, String address, String code, String city, String carType, String carNumber, String comment, boolean here, List<Resa> resas) {
        super(id, name, surname, photo, created, email, phone, nation, birthday, birthdayCity, profession, sex, address, code, city, carType, carNumber, comment, here);
        this.resas = resas;
    }

    public List<Resa> getResas() {
        return resas;
    }
}
