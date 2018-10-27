package com.webapp.tas.objects;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public @Data class User {
    String login;
    String password = "restricted";
    String avatar;
    int admin_perm;


}
