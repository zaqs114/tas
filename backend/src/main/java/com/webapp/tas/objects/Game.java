package com.webapp.tas.objects;

import lombok.Data;

import java.sql.Date;


public @Data class Game {
    int gameid;
    String icon;
    String title;
    String description;
    Date launch_date;
    String publisher;
    String screen;
    String platform;
    String genre;

}
