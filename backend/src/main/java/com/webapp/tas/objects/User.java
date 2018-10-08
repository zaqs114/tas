package com.webapp.tas.objects;

import lombok.Data;

public @Data class User {
    String login;
    String password;
    String avatar;
    int admin_perm;
}
