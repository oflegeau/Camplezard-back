package com.lezardrieux.back.front.model;

import java.util.List;

public class PlaceSlot extends PlaceCard {

    private List<TimeSlot> timeSlots;

    public PlaceSlot() {}
    public PlaceSlot(String id, String code, int type, String comment, int line, int zone, int status, int famous, double price, boolean van, boolean water, boolean elect, List<TimeSlot> timeSlots) {
        super(id, code, type, comment, line, zone, status, famous, price, van, water, elect);
        this.timeSlots = timeSlots;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }
}
