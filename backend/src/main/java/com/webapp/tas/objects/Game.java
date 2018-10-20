package com.webapp.tas.objects;

import lombok.Data;

import java.util.Date;


public @Data class Game {
    int gameid;
    String icon;
    String title;
    String description;
    Date launchdate;
    String publisher;
    String screen;
    String platform;
    String genre;

}
