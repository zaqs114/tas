package com.webapp.tas.objects;

import lombok.Data;

public @Data class Review {
    int reviewID;
    String title;
    String content;
    int rate;
    String userID;
    int gameID;
}
