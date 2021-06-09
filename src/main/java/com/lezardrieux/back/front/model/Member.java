package com.lezardrieux.back.front.model;

public class Member implements Comparable<Member>  {

    String id;
    String name;
    String surname;

    //---------------------------------------------------//
    // CONSTRUCTOR
    //---------------------------------------------------//

    public Member() {
    }

    public Member(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int compareTo(Member compareTo) {
        return this.getName().compareTo(compareTo.getName());
    }

    //---------------------------------------------------//
    // GET
    //---------------------------------------------------//

    public String getId() {
        return id;
    }

    public Member setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    //---------------------------------------------------//
    // TO STRING  without Jointures
    //---------------------------------------------------//

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
