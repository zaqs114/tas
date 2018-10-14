package com.webapp.tas;

import com.webapp.tas.objects.User;
import com.webapp.tas.tables.daos.UsersDao;
import com.webapp.tas.tables.interfaces.IUsers;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.webapp.tas.Tables.USERS;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DSLContext jooq;

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = jooq.fetchOne(USERS, USERS.LOGIN.eq(login)).into(User.class);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return user;
    }
}