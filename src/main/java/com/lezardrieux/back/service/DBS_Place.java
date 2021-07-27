package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Place;
import com.lezardrieux.back.front.model.PagePlace;
import com.lezardrieux.back.front.model.Place;

import java.util.List;

public interface DBS_Place {

    DAO_Place getBack(Place obj);

    Place get(String id);
    DAO_Place getDAO(String id);

    List<Place> getList();

    PagePlace getPage(int zone);

    void create();
}
