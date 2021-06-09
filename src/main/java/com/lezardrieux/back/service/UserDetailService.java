package com.lezardrieux.back.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.lezardrieux.back.back.modelDAO.DAO_ConnectAccess;
import com.lezardrieux.back.back.modelDAO.DAO_ConnectRole;
import com.lezardrieux.back.back.repoDAO.Repo_ConnectAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    Repo_ConnectAccess repoConnectAccess;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String idToken) throws UsernameNotFoundException {

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            DAO_ConnectAccess access = repoConnectAccess.findByConnect_Id(UUID.fromString(decodedToken.getName()));

            // if (trace) LOGGER.info("Found User: " + access.getId());

            // [ROLE_USER, ROLE_ADMIN,..]
            List<DAO_ConnectRole> roleNames = access.getRoles();
            List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

            if (!roleNames.isEmpty()) {
                for (DAO_ConnectRole role : roleNames) {
                    // ROLE_USER, ROLE_ADMIN,..
                    grantList.add(new SimpleGrantedAuthority(role.getRole()));
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String EncryptedPassword = encoder.encode(decodedToken.getName());

            return new org.springframework.security.core.userdetails.User(  decodedToken.getName(),
                                                                            EncryptedPassword,
                                                                            grantList);

        } catch (FirebaseAuthException e) {
            //e.printStackTrace();
            LOGGER.error("AppUserDetailsService/Firebase decode token error " + e.getMessage());
            throw new UsernameNotFoundException("AppUserDetailsService/Firebase decode token error " + e.getMessage());
        }
    }
}
