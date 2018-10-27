package com.webapp.tas.controllers;

import com.webapp.tas.objects.User;
import com.webapp.tas.s3.AmazonClient;
import com.webapp.tas.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.webapp.tas.Tables.USERS;


@RestController
@CrossOrigin(origins = "http://localhost:63343")

public class UserController {

    @Autowired
    private DSLContext jooq;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    UserController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @GetMapping("/users")
    public List<User> usersList() {
        return jooq.select(USERS.LOGIN, USERS.AVATAR).from(USERS).fetchInto(User.class);
    }

    @PostMapping("/register")
    public void registerUser(@RequestPart(value = "login") String login,
                             @RequestPart(value = "password") String password,
                             @RequestPart(value = "avatar", required = false) MultipartFile file) {
        UsersRecord ur = jooq.newRecord(USERS);
        if (file != null){
            ur.setAvatar(amazonClient.uploadFile(file));
        }else{
            ur.setAvatar("");
        }

        ur.setLogin(login);
        ur.setPassword(passwordEncoder.encode(password));
        ur.setAdminPerm(0);
        ur.store();
    }

    //checking nickname of logged user
    //todo: wywali sie jesli nikt nie jest zalogowany
    @GetMapping("/loggedUsername")
    public String loggedUserName(Authentication authentication) {
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
