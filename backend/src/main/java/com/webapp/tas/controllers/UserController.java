package com.webapp.tas.controllers;

import com.webapp.tas.objects.User;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.webapp.tas.tables.Users.USERS;

@RestController
public class UserController {

    @Autowired
    private DSLContext jooq;

    @GetMapping("/users")
    public List<User> users() {
        return jooq.select().from(USERS).fetchInto(User.class);
    }
}
