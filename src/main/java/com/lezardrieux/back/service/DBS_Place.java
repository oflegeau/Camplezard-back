package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Place;
import com.lezardrieux.back.front.model.PagePlace;
import com.lezardrieux.back.front.model.Place;

import java.util.List;

public interface DBS_Place {

    DAO_Place getBack(Place obj);

    Place getPlace_ById(String id);
    DAO_Place getPlace_DAO_ById(String id);

    List<Place> getPlace_List();

    PagePlace getPlace_Page(int zone);
}
