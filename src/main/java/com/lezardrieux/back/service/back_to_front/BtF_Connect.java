package com.lezardrieux.back.service.back_to_front;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.lezardrieux.back.back.modelDAO.DAO_Connect;
import com.lezardrieux.back.back.modelDAO.DAO_ConnectRole;
import com.lezardrieux.back.back.repoDAO.Repo_Connect;
import com.lezardrieux.back.back.repoDAO.Repo_ConnectRole;
import com.lezardrieux.back.front.model.Connect;
import com.lezardrieux.back.front.model.PageConnect;
import com.lezardrieux.back.front.model.Reponse;
import com.lezardrieux.back.service.DBS_Connect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.lezardrieux.back.BackApplication.trace;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class BtF_Connect implements DBS_Connect {

    @Autowired
    Repo_Connect repo_connect;
    @Autowired
    Repo_ConnectRole repo_connectRole;

    //------------------------------------------------------------------------------//


    //------------------------------------------------------------------------------//

    @Override
    public DAO_Connect getBack(Connect obj) {
        return new DAO_Connect( obj.getIdFront(),
                                UUID.fromString("00000000-0000-0000-0000-000000000000"),
                                obj.getRole(),
                                obj.isEmailVerified(),
                                obj.getLastConnexion(),
                                obj.getEmail().substring(0, Math.min(128, obj.getEmail().length())),
                                obj.getPhone().substring(0, Math.min(14, obj.getPhone().length())),
                                obj.getName().substring(0, Math.min(20, obj.getName().length())),
                                obj.getSurname().substring(0, Math.min(20, obj.getSurname().length())),
                                obj.getCreated(),
                                obj.getPhoto(),
                                obj.getNation(),
                                obj.getBirthday(),
                                obj.isSex(),
                                obj.getAddress().substring(0, Math.min(128, obj.getAddress().length())),
                                obj.getCode().substring(0, Math.min(5, obj.getCode().length())),
                                obj.getCity().substring(0, Math.min(30, obj.getCity().length())));
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Connect getConnect_ById(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Connect> option = repo_connect.findById(UUID.fromString(id));
        return option.map(DAO_Connect::getConnect).orElse(null);
    }

    @Override
    @Transactional
    public Connect getConnect_ByIdFront(String idFront) {
        if (idFront.equals("")) return null;
        Optional<DAO_Connect> option = repo_connect.findByIdFront(idFront);
        return option.map(DAO_Connect::getConnect).orElse(null);
    }

    @Override
    @Transactional
    public DAO_Connect getConnect_DAO_ById(String id) {
        if (id.equals("")) return null;
        Optional<DAO_Connect> option = repo_connect.findById(UUID.fromString(id));
        return option.orElse(null);
    }

    @Override
    @Transactional
    public DAO_Connect getConnect_DAO_ByIdFront(String idFront) {
        if (idFront.equals("")) return null;
        Optional<DAO_Connect> option = repo_connect.findByIdFront(idFront);
        return option.orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public List<Connect> getConnect_List_ByAll() {
        List<DAO_Connect> _TList = repo_connect.findAll();
        List<Connect> _List = new ArrayList<>();
        for (DAO_Connect _TObj : _TList) {
            _List.add(_TObj.getConnect());
        }
        return _List;
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public PageConnect getConnect_Page_ByAll(int page,
                                             int size,
                                             int filter,
                                             boolean sortAsc,
                                             String sortName) {
        Pageable pageable = null;
        if (sortAsc)
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortName)));
        else pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(sortName)));

        Page<DAO_Connect> _Page = repo_connect.findAllByRoleGreaterThanEqual(filter, pageable);

        List<DAO_Connect> _TList = _Page.getContent();
        List<Connect> _List = new ArrayList<>();

        for (DAO_Connect _TObj : _TList) {
            _List.add(_TObj.getConnect());
        }

        return new PageConnect( _List,
                                _Page.getNumber(),
                                _Page.getNumberOfElements(),
                                _Page.isEmpty(),
                                _Page.isFirst(),
                                _Page.isLast(),
                                _Page.getSort().isSorted(),
                                _Page.getTotalElements(),
                                _Page.getTotalPages());
    }

    //------------------------------------------------------------------------------//
    // POST
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse create(String token, String name, String surname, String phone) throws UsernameNotFoundException {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

            // test si 1ere user -> ADMIN //
            // ------------------------------------------------- //
            boolean bfirst = false;
            List<DAO_Connect> list = repo_connect.findAll();
            if (list.isEmpty()) bfirst = true;
            int role = 0x01;
            if (bfirst) {
                role = 0x01 | 0x02 | 0x04 | 0x08;
            }

            // Ajout de Id dans Firebase :                       //
            // ------------------------------------------------- //
            UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(decodedToken.getUid()).setDisplayName(name);
            UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
            if (userRecord == null) return new Reponse(HttpStatus.NOT_FOUND, "Impossible de modifier Firebase");
     /*       UserRecord.UpdateRequest request1 = new UserRecord.UpdateRequest(decodedToken.getUid()).setPhoneNumber(phone);
            UserRecord userRecord1 = FirebaseAuth.getInstance().updateUser(request1);
            if (userRecord1 == null) return new Reponse(HttpStatus.NOT_FOUND, "Impossible de modifier Firebase");
    */
            // ------------------------------------------------- //
            // Ajout d'un UserAuth                               //
            // ------------------------------------------------- //
            DAO_Connect _TObjConnect = new DAO_Connect( decodedToken.getUid(),
                                                        UUID.fromString("00000000-0000-0000-0000-000000000000"),
                                                        role,
                                                        decodedToken.isEmailVerified(),
                                                        new Date(),
                                                        decodedToken.getEmail(),
                                                        phone,
                                                        name,
                                                        surname,
                                                        new Date(),
                                                        "",
                                                        1,
                                                        null,
                                                        false,
                                                        "",
                                                        "",
                                                        "");
            _TObjConnect = repo_connect.save(_TObjConnect);
            if (trace) LOGGER.warn(_TObjConnect.toString());

            // Ajout d'un TRole                                  //
            // ------------------------------------------------- //
            _TObjConnect.addRole(new DAO_ConnectRole("ROLE_USER"));

            // si 1er élément : alors ADMIN et MANAGER //
            if (bfirst) {
                // ajout des roles //
                _TObjConnect.addRole(new DAO_ConnectRole("ROLE_MEMBER"));
                _TObjConnect.addRole(new DAO_ConnectRole("ROLE_MANAGER"));
                _TObjConnect.addRole(new DAO_ConnectRole("ROLE_ADMIN"));
            }

            return new Reponse(HttpStatus.CREATED, "", 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UsernameNotFoundException("BtF_Connect/create/error " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
    // PUT
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Connect update(Connect obj) {
        try {
            DAO_Connect dao_connect = getConnect_DAO_ByIdFront(obj.getIdFront());
            if (dao_connect == null) {
                LOGGER.error("DAO_Connect null/ID = " + obj.getIdFront());
               return null;
            }

            dao_connect.setEmailVerified(obj.isEmailVerified());
            dao_connect.setName(obj.getName().substring(0, Math.min(20, obj.getName().length())));
            dao_connect.setSurname(obj.getSurname().substring(0, Math.min(20, obj.getSurname().length())));
            dao_connect.setPhone(obj.getPhone().substring(0, Math.min(14, obj.getPhone().length())));
            dao_connect.setPhoto(obj.getPhoto());
            dao_connect.setNation(obj.getNation());
            dao_connect.setBirthday(obj.getBirthday());
            dao_connect.setSex(obj.isSex());
            dao_connect.setAddress(obj.getAddress().substring(0, Math.min(128, obj.getAddress().length())));
            dao_connect.setCode(obj.getCode().substring(0, Math.min(5, obj.getCode().length())));
            dao_connect.setCity(obj.getCity().substring(0, Math.min(30, obj.getCity().length())));

            dao_connect = repo_connect.save(dao_connect);
            if (trace) LOGGER.warn(dao_connect.toString());
            return dao_connect.getConnect();
        } catch (Exception e) {
            LOGGER.error("BtF_Connect/update/error " + e.getMessage());
            return null;
        }
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Connect update_GetLast() throws UsernameNotFoundException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (userDetails == null)
                throw new UsernameNotFoundException("BtF_Connect/update_GetLast/error");

            Optional<DAO_Connect> _option = repo_connect.findById(UUID.fromString(userDetails.getUsername()));
            if (_option.isEmpty())
                throw new UsernameNotFoundException("BtF_Connect/update_GetLast/error");

            DAO_Connect _TObj = _option.get();
            _TObj.setLastConnexion(new Date());
            _TObj.setEmailVerified(true);
            _TObj = repo_connect.save(_TObj);

            if (trace) LOGGER.warn(_TObj.toString());
            return _TObj.getConnect();
        } catch (Exception e) {
            throw new UsernameNotFoundException("BtF_Connect/update_GetLast/error " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Connect update_Role(String id, int newRole) {
        try {
            DAO_Connect dao_connect = getConnect_DAO_ById(id);
            if (dao_connect == null) {
                LOGGER.error("DAO_Connect null/ID = " + id);
                return null;
            }

            int role = dao_connect.getRole();
            int roleAdd = 0;
            int roleDel = 0;

            switch (newRole) {
                case 4:
                    if ((role & 0x08) != 0x08) roleAdd |= 0x08;
                    if ((role & 0x04) != 0x04) roleAdd |= 0x04;
                    if ((role & 0x02) != 0x02) roleAdd |= 0x02;
                    if ((role & 0x01) != 0x01) roleAdd |= 0x01;
                    break;

                case 3:
                    if ((role & 0x08) == 0x08) roleDel |= 0x08;

                    if ((role & 0x04) != 0x04) roleAdd |= 0x04;
                    if ((role & 0x02) != 0x02) roleAdd |= 0x02;
                    if ((role & 0x01) != 0x01) roleAdd |= 0x01;
                    break;

                case 2:
                    if ((role & 0x08) == 0x08) roleDel |= 0x08;
                    if ((role & 0x04) == 0x04) roleDel |= 0x04;

                    if ((role & 0x02) != 0x02) roleAdd |= 0x02;
                    if ((role & 0x01) != 0x01) roleAdd |= 0x01;
                    break;

                case 1:
                    if ((role & 0x08) == 0x08) roleDel |= 0x08;
                    if ((role & 0x04) == 0x04) roleDel |= 0x04;
                    if ((role & 0x02) == 0x02) roleDel |= 0x02;

                    if ((role & 0x01) != 0x01) roleAdd |= 0x01;
                    break;
            }

            return update_RolePost(dao_connect, roleAdd, roleDel);
        } catch (Exception e) {
            LOGGER.error("BtF_Connect/update_Role/error " + e.getMessage());
            return null;
        }
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Connect update_RolePost(DAO_Connect dao_connect, int roleAdd, int roleDel) {
        try {
            int role = dao_connect.getRole();

            // suppression de roles //
            if (roleDel != 0) {
                List<DAO_ConnectRole> rolesToDelete = new ArrayList<>();
                for (DAO_ConnectRole _Objrole : dao_connect.getRoles()) {
                    if ((((roleDel & 0x01) == 0x01) && _Objrole.getRole().equals("ROLE_USER")) ||
                            (((roleDel & 0x02) == 0x02) && _Objrole.getRole().equals("ROLE_MEMBER")) ||
                            (((roleDel & 0x04) == 0x04) && _Objrole.getRole().equals("ROLE_MANAGER")) ||
                            (((roleDel & 0x08) == 0x08) && _Objrole.getRole().equals("ROLE_ADMIN"))) {

                        if (trace) LOGGER.warn("Del Role:" + _Objrole.toString());
                        repo_connectRole.deleteById(_Objrole.getId());
                        rolesToDelete.add(_Objrole);
                    }
                }

                for (DAO_ConnectRole _Objrole : rolesToDelete) {
                    dao_connect.removeRole(_Objrole);
                }

                role ^= roleDel;
            }

            // ajout de roles //
            if (roleAdd != 0) {

                if ((roleAdd & 0x01) == 0x01) {
                    dao_connect.addRole(new DAO_ConnectRole("ROLE_USER"));
                }
                if ((roleAdd & 0x02) == 0x02) {
                    dao_connect.addRole(new DAO_ConnectRole("ROLE_MEMBER"));
                }
                if ((roleAdd & 0x04) == 0x04) {
                    dao_connect.addRole(new DAO_ConnectRole("ROLE_MANAGER"));
                }
                if ((roleAdd & 0x08) == 0x08) {
                    dao_connect.addRole(new DAO_ConnectRole("ROLE_ADMIN"));
                }

                role |= roleAdd;
            }

            dao_connect.setRole(role);
            dao_connect = repo_connect.save(dao_connect);

            if (trace) LOGGER.warn(dao_connect.toString());
            return dao_connect.getConnect();
        } catch (Exception e) {
            return null;
        }
    }

    // ----------------------------------------------------------------------------- //
    //                                  DELETE                                         //
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse delete(String idFront)  {

        if (idFront.equals(""))
            return new Reponse(HttpStatus.NOT_FOUND, "ERROR delete object null");

        try {
            DAO_Connect dao_connect = getConnect_DAO_ByIdFront(idFront);
            if (dao_connect == null)
                return new Reponse(HttpStatus.NOT_FOUND,"BtF_Connect/delete/error");

            // delete Firebase //
            FirebaseAuth.getInstance().deleteUser(dao_connect.getIdFront());

            // supprimer le role de Membre //
            update_Role(dao_connect.getSId(), 1);

            // delete TConnect //
            repo_connect.deleteById(dao_connect.getId());

            return new Reponse(HttpStatus.OK, idFront, 0L);
        } catch (Exception e) {
            return new Reponse(HttpStatus.NOT_FOUND,"BtF_Connect/delete/error " + e.getMessage());
        }
    }

    private boolean checkRole(int role, int position) {
        var val = (int) Math.pow(2, position);
        var n = role & val;
        if (n == val) return true;
        return false;
    }
}
