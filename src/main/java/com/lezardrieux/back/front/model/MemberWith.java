package com.lezardrieux.back.front.model;

import java.util.Date;

public class MemberWith {

    private String id;
    private String name;
    private String surname;
    private boolean sex;
    private Date birthday;

    public MemberWith() {}
    public MemberWith(String id, String name, String surname, boolean sex, Date birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "MemberWith{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                '}';
    }
}
