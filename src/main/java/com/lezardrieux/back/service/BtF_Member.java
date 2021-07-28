package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Member;
import com.lezardrieux.back.back.repoDAO.Repo_Member;
import com.lezardrieux.back.front.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.lezardrieux.back.BackApplication.trace;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class BtF_Member implements DBS_Member {

    @Autowired
    Repo_Member repo_member;
    @Autowired
    DBS_Connect dbs_connect;
    @Autowired
    DBS_Resa dbs_resa;

    //------------------------------------------------------------------------------//

    private DAO_Member getBack(MemberCard obj) {
        return new DAO_Member(  obj.getName().substring(0, Math.min(20, obj.getName().length())),
                                obj.getSurname().substring(0, Math.min(20, obj.getSurname().length())),
                                obj.getMemberPhoto().getPhoto(),
                                obj.getMemberPhoto().getCreated(),
                                obj.isHere(),
                                obj.getEmail().substring(0, Math.min(128, obj.getEmail().length())),
                                obj.getPhone().substring(0, Math.min(14, obj.getPhone().length())),
                                obj.getNation(),
                                obj.getBirthday(),
                                obj.getBirthdayCity().substring(0, Math.min(30, obj.getBirthdayCity().length())),
                                obj.getProfession().substring(0, Math.min(30, obj.getProfession().length())),
                                obj.isSex(),
                                obj.getAddress().substring(0, Math.min(128, obj.getAddress().length())),
                                obj.getCode().substring(0, Math.min(5, obj.getCode().length())),
                                obj.getCity().substring(0, Math.min(30, obj.getCity().length())),
                                obj.getCarType(),
                                obj.getCarNumber(),
                                obj.getComment().substring(0, Math.min(255, obj.getComment().length())));
    }

    private MemberResa getMemberResa(DAO_Member obj) {
        return new MemberResa(  obj.getId().toString(),
                                obj.getName(),
                                obj.getSurname(),
                                obj.getMemberPhoto().getPhoto(),
                                obj.getMemberPhoto().getCreated(),
                                obj.getEmail(),
                                obj.getPhone(),
                                obj.getNation(),
                                obj.getBirthday(),
                                obj.getBirthdayCity(),
                                obj.getProfession(),
                                obj.isSex(),
                                obj.getAddress(),
                                obj.getCode(),
                                obj.getCity(),
                                obj.getCarType(),
                                obj.getCarNumber(),
                                obj.getComment(),
                                obj.isHere(),
                                dbs_resa.getList(obj.getResas()));
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Member get(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Member> option = repo_member.findById(UUID.fromString(id));
        return option.map(DAO_Member::getMember).orElse(null);
    }

    @Override
    @Transactional
    public MemberPhoto get_Photo(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Member> option = repo_member.findById(UUID.fromString(id));
        return option.map(DAO_Member::getMemberPhoto).orElse(null);
    }

    @Override
    @Transactional
    public MemberCard get_Card(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Member> option = repo_member.findById(UUID.fromString(id));
        return option.map(DAO_Member::getMemberCard).orElse(null);
    }

    @Override
    @Transactional
    public MemberResa get_Resa(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Member> option = repo_member.findById(UUID.fromString(id));
        return option.map(this::getMemberResa).orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_Member getDAO(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Member> option = repo_member.findById(UUID.fromString(id));
        return option.orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    public List<Member> getList(List<DAO_Member> list) {
        List<Member> _List = new ArrayList<>();
        for (DAO_Member _TObj : list) {
            _List.add(_TObj.getMember());
        }
        return _List;
    }

    @Override
    @Transactional
    public List<Member> getList() {
        List<DAO_Member> _TList = repo_member.findAll();
        List<Member> _List = new ArrayList<>();
        for (DAO_Member _TObj : _TList) {
            _List.add(_TObj.getMember());
        }
        return _List;
    }

    @Override
    @Transactional
    public List<MemberPhoto> getList_Photo() {
        List<DAO_Member> _TList = repo_member.findAll();
        List<MemberPhoto> _List = new ArrayList<>();
        for (DAO_Member _TObj : _TList) {
            _List.add(_TObj.getMemberPhoto());
        }
        return _List;
    }

    @Override
    @Transactional
    public PageMember getPage_Card(int page,
                                     int size,
                                     int typeNation,
                                     boolean sortAsc,
                                     String sortName) {

        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.set(Calendar.YEAR, 2099);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        int[] status = {0, 1, 2};
        status[0] = repo_member.findAll().size();
        status[1] = 0;
        status[2] = 0;

        Pageable pageable = null;
        if (sortAsc)
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortName)));
        else pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(sortName)));

        Page<DAO_Member> _Page = null;
        if (typeNation == 0)
            _Page = repo_member.findAll(pageable);
        else
            _Page = repo_member.findAllByNationEquals(typeNation, pageable);

        if (_Page == null) return null;

        List<DAO_Member> _TList = _Page.getContent();
        List<MemberCard> _List = new ArrayList<>();

        for (DAO_Member _TObj : _TList) {
            _List.add(_TObj.getMemberCard());
        }

        return new PageMember(  _List,
                                _Page.getNumber(),
                                _Page.getNumberOfElements(),
                                _Page.isEmpty(),
                                _Page.isFirst(),
                                _Page.isLast(),
                                _Page.getSort().isSorted(),
                                _Page.getTotalElements(),
                                _Page.getTotalPages(),
                                status);
    }

    //------------------------------------------------------------------------------//
    // POST
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse create(MemberCard obj) {
        try {
            // ----------------------------------------- //
            // sauvegarde sans jointure pour création ID //
            // ----------------------------------------- //
            DAO_Member dao_member = repo_member.save(getBack(obj));
            // ----------------------------------------- //
            if (trace) LOGGER.warn(dao_member.toString());
            return new Reponse(HttpStatus.CREATED, dao_member.getId().toString(), 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Member/create" + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
    // PUT
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse update(MemberCard obj) {
        try {
            // ----------------------------------------- //
            // récupération en base avant update         //
            // ----------------------------------------- //
            DAO_Member dao_member = getDAO(obj.getId());
            if (dao_member == null) {
                LOGGER.error("Member null/ID = " + obj.getId());
                return new Reponse(HttpStatus.NOT_FOUND, "Member null/ID = " + obj.getId());
            }
            // ----------------------------------------- //
            // mise à jour                               //
            // ----------------------------------------- //
            dao_member = dao_member
                    .setName(obj.getName().substring(0, Math.min(20, obj.getName().length())))
                    .setSurname(obj.getSurname().substring(0, Math.min(20, obj.getSurname().length())))
                    .setPhoto(obj.getPhoto())
                    .setCreated(obj.getCreated())
                    .setEmail(obj.getEmail().substring(0, Math.min(128, obj.getEmail().length())))
                    .setPhone(obj.getPhone().substring(0, Math.min(14, obj.getPhone().length())))
                    .setNation(obj.getNation())
                    .setBirthday(obj.getBirthday())
                    .setBirthdayCity(obj.getBirthdayCity().substring(0, Math.min(30, obj.getBirthdayCity().length())))
                    .setProfession(obj.getProfession().substring(0, Math.min(30, obj.getProfession().length())))
                    .setSex(obj.isSex())
                    .setAddress(obj.getAddress().substring(0, Math.min(128, obj.getAddress().length())))
                    .setCode(obj.getCode().substring(0, Math.min(5, obj.getCode().length())))
                    .setCity(obj.getCity().substring(0, Math.min(30, obj.getCity().length())))
                    .setCarType(obj.getCarType())
                    .setCarNumber(obj.getCarNumber())
                    .setComment(obj.getComment().substring(0, Math.min(255, obj.getComment().length())));

            // ----------------------------------------- //
            repo_member.save(dao_member);

            if (trace) LOGGER.warn(dao_member.toString());
            return new Reponse(HttpStatus.ACCEPTED, dao_member.getId().toString(), 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Member/update " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
    // DELETE
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public void deleteAll() {
        try {
            for (Iterator<DAO_Member> iter = repo_member.findAll().listIterator(); iter.hasNext(); ) {
                DAO_Member dao_member = iter.next();
                repo_member.deleteById(dao_member.getId());
                iter.remove();
            }

        } catch (Exception e) {
        LOGGER.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reponse delete(String id) {
        try {
            DAO_Member dao_member = getDAO(id);
            if (dao_member == null) {
                LOGGER.error("DAO_Member null/ID = " + id);
                return new Reponse(HttpStatus.NOT_FOUND, "DAO_Member null/ID = " + id);
            }
            // ----------------------------------------- //
            repo_member.deleteById(UUID.fromString(id));

            if (trace) LOGGER.warn(id);
            return new Reponse(HttpStatus.OK, id, 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Member/delete " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------------------- //
}
