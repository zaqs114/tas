package com.webapp.tas.objects;

import lombok.Data;

@Data
public class UserShort {
    private String login;
    private String password = "restricted";
}
