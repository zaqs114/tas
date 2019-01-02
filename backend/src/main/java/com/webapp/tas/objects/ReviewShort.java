package com.webapp.tas.objects;

import lombok.Data;

@Data
public class ReviewShort {
    private String title;
    private String content;
    private int rate;
    private String userID;
    private int gameID;
}
