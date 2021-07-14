package com.lezardrieux.back.front.model;

import java.util.Date;

public class TimeSlot {

    private Long id;
    private Date day;
    private int status;

    public TimeSlot() {}
    public TimeSlot(Long id, Date day, int status) {
        this.id = id;
        this.day = day;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Date getDay() {
        return day;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "id=" + id +
                ", day=" + day +
                ", status=" + status +
                '}';
    }
}
