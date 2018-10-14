package com.webapp.tas.controllers;

import com.webapp.tas.objects.User;
import com.webapp.tas.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/users")
    public List<User> usersList() {
        return jooq.select().from(USERS).fetchInto(User.class);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        UsersRecord ur = jooq.newRecord(USERS);
        ur.setAvatar(user.getAvatar());
        ur.setLogin(user.getLogin());
        ur.setPassword(passwordEncoder.encode(user.getPassword()));
        ur.setAdminPerm(0);
        ur.store();
    }

    @PostMapping("/login")
    public void loginUser(){
        
    }
}
