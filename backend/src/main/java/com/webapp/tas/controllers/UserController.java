package com.webapp.tas.controllers;

import com.webapp.tas.Tables;
import com.webapp.tas.objects.User;
import com.webapp.tas.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.webapp.tas.Tables.USERS;


@RestController
@CrossOrigin(origins = "http://localhost:63343")

public class UserController {

    @Autowired
    private DSLContext jooq;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //todo co jak nie znajdzie usera
    @GetMapping("/users")
    public List<User> usersList() {
        List<User> userList = jooq.select().from(USERS).fetchInto(User.class);

        return jooq.select(USERS.LOGIN, USERS.AVATAR).from(USERS).fetchInto(User.class);
    }

    //todo co jak jakies pole do rejestracji puste
    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        UsersRecord ur = jooq.newRecord(USERS);
        ur.setAvatar(user.getAvatar());
        ur.setLogin(user.getLogin());
        ur.setPassword(passwordEncoder.encode(user.getPassword()));
        ur.setAdminPerm(0);
        ur.store();
    }

    //checking nickname of logged user
    //todo: wywali sie jesli nikt nie jest zalogowany
    @GetMapping("/loggedUsername")
    public String loggedUserName(Authentication authentication){
        return authentication.getName();
    }

    @GetMapping("/userDetails/{id}")
    public User getLoggedUserDetails(@PathVariable String id){
        User user = new User();
        UsersRecord ur = jooq.fetchOne(USERS, USERS.LOGIN.eq(id));
        user.setLogin(ur.getLogin());
        user.setPassword("restricted");
        user.setAvatar(ur.getAvatar());
        return user;
    }


}
