package com.lezardrieux.back.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.lezardrieux.back.back.modelDAO.DAO_Connect;
import com.lezardrieux.back.back.modelDAO.DAO_ConnectRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    DBS_Connect dbs_connect;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            DAO_Connect dao_connect = dbs_connect.getConnect_DAO_ByIdFront(decodedToken.getUid());

            if (dao_connect == null) {
                LOGGER.warn("loadUserByUsername dao_connect null !");
                throw new UsernameNotFoundException("loadUserByUsername dao_connect null !");
            }

            // [ROLE_USER, ROLE_ADMIN,..]
            List<DAO_ConnectRole> roleNames = dao_connect.getRoles();
            List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

            if (!roleNames.isEmpty()) {
                for (DAO_ConnectRole role : roleNames) {
                    // ROLE_USER, ROLE_ADMIN,..
                    grantList.add(new SimpleGrantedAuthority(role.getRole()));
                }
            }

            Date date = new Date();
            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(date);
            String password =  calendar.get(Calendar.DATE) + calendar.get(Calendar.MONTH)
                            + decodedToken.getUid().substring(0,10) + calendar.get(Calendar.YEAR)
                            + decodedToken.getUid().substring(15,20);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String EncryptedPassword = encoder.encode(password);

            return new org.springframework.security.core.userdetails.User(  dao_connect.getSId(),
                                                                            EncryptedPassword,
                                                                            grantList);

        } catch (FirebaseAuthException e) {
            //e.printStackTrace();
            LOGGER.error("AppUserDetailsService/Firebase decode token error " + e.getMessage());
            throw new UsernameNotFoundException("AppUserDetailsService/Firebase decode token error " + e.getMessage());
        }
    }
}
