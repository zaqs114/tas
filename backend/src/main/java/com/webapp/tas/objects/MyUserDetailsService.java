package com.webapp.tas.objects;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.webapp.tas.Tables.USERS;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DSLContext jooq;

    @Override
    public UserDetails loadUserByUsername(String login){
        try{
            MyUserPrincipal user = jooq.fetchOne(USERS, USERS.LOGIN.eq(login)).into(MyUserPrincipal.class);
            return user;
        }catch(Exception e){
            throw new InternalAuthenticationServiceException("User is not logged in.", e);
        }
        //TODO wywala sie jak podasz nieistniejacego usera, dodaj jakis error handling
    }
}