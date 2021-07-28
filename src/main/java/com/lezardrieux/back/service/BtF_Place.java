package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Place;
import com.lezardrieux.back.back.repoDAO.Repo_Place;
import com.lezardrieux.back.front.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.lezardrieux.back.BackApplication.*;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class BtF_Place implements DBS_Place {

    @Autowired
    Repo_Place repo_place;

    //------------------------------------------------------------------------------//

    private DAO_Place getBack(PlaceCard obj) {
        return new DAO_Place(obj.getCode().substring(0, Math.min(10, obj.getCode().length())),
                             obj.getType(),
                             obj.getComment().substring(0, Math.min(64, obj.getComment().length())),
                             obj.getLine(),
                             obj.getZone(),
                             obj.getStatus(),
                             obj.getFamous(),
                             obj.getPrice(),
                             obj.isVan(),
                             obj.isWater(),
                             obj.isElect());
    }

    //------------------------------------------------------------------------------//

    private Place getPlace(DAO_Place obj) {
        return new Place(obj.getId().toString(),
                        obj.getCode(),
                        obj.getType());
    }

    //------------------------------------------------------------------------------//

    private PlaceCard getPlaceCard(DAO_Place obj) {
        return new PlaceCard(obj.getId().toString(),
                            obj.getCode(),
                            obj.getType(),
                            obj.getComment(),
                            obj.getLine(),
                            obj.getZone(),
                            obj.getStatus(),
                            obj.getFamous(),
                            obj.getPrice(),
                            obj.isVan(),
                            obj.isWater(),
                            obj.isElect());
    }

    //------------------------------------------------------------------------------//

    private PlaceSlot getPlaceSlot(DAO_Place obj, String keyMin, String keyMax) {
        return new PlaceSlot(obj.getId().toString(),
                            obj.getCode(),
                            obj.getType(),
                            obj.getComment(),
                            obj.getLine(),
                            obj.getZone(),
                            obj.getStatus(),
                            obj.getFamous(),
                            obj.getPrice(),
                            obj.isVan(),
                            obj.isWater(),
                            obj.isElect(),
                            null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public PlaceCard get(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Place> option = repo_place.findById(UUID.fromString(id));
        return option.map(this::getPlaceCard).orElse(null);
    }

    @Override
    @Transactional
    public PlaceSlot get(String id, String keyMin, String keyMax) {
        if (id.equals("")) return null;
        Optional<DAO_Place> option = repo_place.findById(UUID.fromString(id));
        return option.map(dao -> getPlaceSlot(dao, keyMin, keyMax)).orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_Place getDAO(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Place> option = repo_place.findById(UUID.fromString(id));
        return option.orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public List<Place> getList() {
        List<DAO_Place> _TList = repo_place.findAll();
        List<Place> _List = new ArrayList<>();
        for (DAO_Place _TObj : _TList) {
            _List.add(getPlace(_TObj));
        }
        return _List;
    }

    @Override
    @Transactional
    public List<PlaceCard> getList_Card() {
        List<DAO_Place> _TList = repo_place.findAll();
        List<PlaceCard> _List = new ArrayList<>();
        for (DAO_Place _TObj : _TList) {
            _List.add(getPlaceCard(_TObj));
        }
        return _List;
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public PagePlace getPage(int zone) {

        int totalPages = 10;
        boolean isFirst = false;
        if (zone == 0) isFirst = true;
        boolean isLast = false;
        if (zone == (totalPages-1)) isLast = true;

        int[] type = {0, 1, 2, 3, 4, 5};
        type[0] = repo_place.findAll().size();
        if (type[0] == 0) create();
        type[1] = repo_place.findAllByStatus(PLACE_STATE).size();
        type[2] = repo_place.findAllByStatus(PLACE_OFF).size();
        type[3] = repo_place.findAllByStatus(PLACE_BUSY).size();
        type[4] = repo_place.findAllByStatus(PLACE_BOOKED).size();
        type[5] = repo_place.findAllByStatus(PLACE_FREE).size();

        List<DAO_Place> _TList  = repo_place.findAllByZone(zone+1);
        int numberElement = _TList.size();
        List<PlaceCard> _List = new ArrayList<>();

        for (DAO_Place _TObj : _TList) {
            _List.add(getPlaceCard(_TObj));
        }

        return new PagePlace(  _List,
                                zone,
                                numberElement,
                                isFirst,
                                isLast,
                                totalPages,
                                type);
    }

    //------------------------------------------------------------------------------//
    // CREATION
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public void create() {
        try {
            repo_place.save(new DAO_Place("63", 2, "",1, 1, 5, 1, 10, true, true, true));
            repo_place.save(new DAO_Place("64", 1, "",1, 1, 5, 1, 1,  false, true, true));
            repo_place.save(new DAO_Place("65", 1,"", 1, 1, 5, 1, 1,false, true, true));
            repo_place.save(new DAO_Place("66", 1,"", 1, 1, 5, 1,1, false, true, true));

            repo_place.save(new DAO_Place("62", 2,"", 2, 1, 5, 2,23,  true, true, true));
            repo_place.save(new DAO_Place("61", 1,"", 2, 1, 5, 2,45,false, true, true));
            repo_place.save(new DAO_Place("60", 1,"", 2, 1, 5, 1, 65,false, true, true));
            repo_place.save(new DAO_Place("59", 1, "",2, 1, 5, 2,20,  false, true, true));

            repo_place.save(new DAO_Place("67", 2, "",1, 2, 5, 3, 1, true, true, true));
            repo_place.save(new DAO_Place("68", 1,"", 1, 2, 5, 1, 1, false, true, true));
            repo_place.save(new DAO_Place("69", 1,"", 1, 2, 5, 2, 1, false, true, true));
            repo_place.save(new DAO_Place("70", 1, "",1, 2, 5, 3,1, false, true, true));

            repo_place.save(new DAO_Place("58", 2, "",2, 2, 5, 1,23,true, true, true));
            repo_place.save(new DAO_Place("57", 1,"", 2, 2, 5, 4,45, false, true, true));
            repo_place.save(new DAO_Place("56", 1,"", 2, 2, 5, 3, 65,false, true, true));
            repo_place.save(new DAO_Place("55", 1, "",2, 2, 5, 1,20,  false, true, true));

            repo_place.save(new DAO_Place("71", 2, "",1, 3, 5, 3, 1, true, true, true));
            repo_place.save(new DAO_Place("Acceuil", 4, "Ouvert tous les jours (sauf le Dimanche) de 9H à 16H",1, 3, 1, 1, 1, false, true, true));
            repo_place.save(new DAO_Place("Lingerie", 7, "Accès Libre - 5€ par machine",1, 3, 1, 2, 1,false, true, true));
            repo_place.save(new DAO_Place("Sanitaire", 5, "Accès Libre - nettoyés 3 fois par jour",1, 3, 1, 3,1,  false, true, true));

            repo_place.save(new DAO_Place("Douches", 6, "Accès Libre - Merci de laisser les espaces prorpres",2, 3, 1, 1,23, true, true, true));
            repo_place.save(new DAO_Place("54", 2, "",2, 3, 5, 4,45,false, true, true));
            repo_place.save(new DAO_Place("53", 2, "",2, 3, 5, 3, 65, false, true, true));
            repo_place.save(new DAO_Place("52", 2, "",2, 3, 5, 1,20,false, true, true));

            repo_place.save(new DAO_Place("1", 3, "",1, 4, 5, 3, 1,  true, true, true));
            repo_place.save(new DAO_Place("2", 2,"", 1, 4, 2, 1, 1,false, true, true));
            repo_place.save(new DAO_Place("3", 1,"", 1, 4, 5, 2, 1,false, true, true));
            repo_place.save(new DAO_Place("4", 1, "",1, 4, 5, 3,1, false, true, true));

            repo_place.save(new DAO_Place("51", 2,"", 2, 4, 5, 1,23, true, true, true));
            repo_place.save(new DAO_Place("50", 1,"", 2, 4, 5, 4,45, false, true, true));
            repo_place.save(new DAO_Place("49", 1,"", 2, 4, 5, 3, 65,false, true, true));
            repo_place.save(new DAO_Place("48", 2,"", 2, 4, 5, 1,20, false, true, true));

            repo_place.save(new DAO_Place("5", 2, "",1, 5, 5, 3, 1,true, true, true));
            repo_place.save(new DAO_Place("6", 2, "",1, 5, 5, 1, 1,false, true, true));
            repo_place.save(new DAO_Place("7", 2, "",1, 5, 5, 2, 1,false, true, true));
            repo_place.save(new DAO_Place("8", 1, "",1, 5, 5, 3,1, false, true, true));

            repo_place.save(new DAO_Place("47", 2,"", 2, 5, 5, 1,23,  true, true, true));
            repo_place.save(new DAO_Place("46", 1, "",2, 5, 5, 4,45,false, true, true));
            repo_place.save(new DAO_Place("45", 1, "",2, 5, 5, 3, 65,false, true, true));
            repo_place.save(new DAO_Place("44", 1, "",2, 5, 5, 1,20, false, true, true));

            repo_place.save(new DAO_Place("9", 1, "",1, 6, 5, 3, 1,  true, true, true));
            repo_place.save(new DAO_Place("10", 1,"", 1, 6, 5, 1, 1, false, true, true));
            repo_place.save(new DAO_Place("11", 1, "",1, 6, 5, 2, 1, false, true, true));
            repo_place.save(new DAO_Place("12", 1, "",1, 6, 5, 3,1,false, true, true));

            repo_place.save(new DAO_Place("43", 2, "",2, 6, 5, 1,23, true, true, true));
            repo_place.save(new DAO_Place("42", 1, "",2, 6, 5, 4,45, false, true, true));
            repo_place.save(new DAO_Place("41", 1, "",2, 6, 2, 3, 65, false, true, true));
            repo_place.save(new DAO_Place("40", 1, "",2, 6, 5, 1,20,  false, true, true));

            repo_place.save(new DAO_Place("13", 1, "",1, 7, 5, 3, 1, true, true, true));
            repo_place.save(new DAO_Place("14", 1, "",1, 7, 5, 4, 1, false, true, true));
            repo_place.save(new DAO_Place("15", 1, "",1, 7, 5, 4, 1,false, true, true));
            repo_place.save(new DAO_Place("16", 1,"", 1, 7, 5, 3,1,false, true, true));

            repo_place.save(new DAO_Place("39", 2, "",2, 7, 5, 2,23, true, true, true));
            repo_place.save(new DAO_Place("38", 1, "",2, 7, 5, 4,45, false, true, true));
            repo_place.save(new DAO_Place("37", 1, "",2, 7, 5, 3, 65,false, true, true));
            repo_place.save(new DAO_Place("36", 1, "",2, 7, 5, 1,20, false, true, true));

            repo_place.save(new DAO_Place("17", 1, "",1, 8, 5, 3, 1,true, true, true));
            repo_place.save(new DAO_Place("18", 1, "",1, 8, 5, 4, 1, false, true, true));
            repo_place.save(new DAO_Place("19", 1, "",1, 8, 5, 4, 1,false, true, true));
            repo_place.save(new DAO_Place("20", 1, "",1, 8, 5, 3,1, false, true, true));

            repo_place.save(new DAO_Place("35", 2, "",2, 8, 5, 2,23, true, true, true));
            repo_place.save(new DAO_Place("34", 1, "",2, 8, 5, 4,45,false, true, true));
            repo_place.save(new DAO_Place("33", 1, "",2, 8, 5, 3, 65, false, true, true));
            repo_place.save(new DAO_Place("32", 1, "",2, 8, 5, 1,20, false, true, true));

            repo_place.save(new DAO_Place("21", 1, "",1, 9, 5, 3, 1, true, true, true));
            repo_place.save(new DAO_Place("22", 1, "",1, 9, 5, 4, 1,  false, true, true));
            repo_place.save(new DAO_Place("24", 1, "",1, 9, 5, 4, 1,false, true, true));
            repo_place.save(new DAO_Place("26", 1, "",1, 9, 5, 3,1, false, true, true));

            repo_place.save(new DAO_Place("20", 2, "",2, 9, 5, 2,23, true, true, true));
            repo_place.save(new DAO_Place("23", 1, "",2, 9, 5, 4,45, false, true, true));
            repo_place.save(new DAO_Place("25", 1, "",2, 9, 5, 3, 65,false, true, true));
            repo_place.save(new DAO_Place("27", 1,"", 2, 9, 5, 1,20,  false, true, true));

            repo_place.save(new DAO_Place("28", 1, "",1, 10, 5, 3, 1,  true, true, true));
            repo_place.save(new DAO_Place("Mer", 8, "",1, 10, 1, 4, 1,  false, true, true));
            repo_place.save(new DAO_Place("Mer", 8, "",1, 10, 1, 4, 1, false, true, true));
            repo_place.save(new DAO_Place("Mer", 8, "",1, 10, 1, 4, 1, false, true, true));

            repo_place.save(new DAO_Place("31", 2, "",2, 10, 5, 2,23,  true, true, true));
            repo_place.save(new DAO_Place("30", 2, "",2, 10, 5, 4,45, false, true, true));
            repo_place.save(new DAO_Place("29", 2, "",2, 10, 5, 3, 65, false, true, true));
            repo_place.save(new DAO_Place("Mer", 8, "Attention : Pas de surveillance",2, 10, 1, 4, 1,  false, true, true));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
}
