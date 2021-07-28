package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Place;
import com.lezardrieux.back.front.model.PagePlace;
import com.lezardrieux.back.front.model.Place;
import com.lezardrieux.back.front.model.PlaceCard;
import com.lezardrieux.back.front.model.PlaceSlot;

import java.util.List;

public interface DBS_Place {

    PlaceCard get(String id);
    DAO_Place getDAO(String id);
    PlaceSlot get(String id, String keyMin, String keyMax);

    List<PlaceCard> getList_Card();
    List<Place> getList();

    PagePlace getPage(int zone);

    void create();
}
