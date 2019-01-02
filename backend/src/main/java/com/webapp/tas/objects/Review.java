package com.webapp.tas.objects;

import lombok.Data;

public @Data class Review {
    int reviewID;
    String title;
    String content;
    int rate;
    String userID;
    int gameID;

    public String getUserID() {
        return userID;
    }

    public void setUserid(String userID) {
        this.userID = userID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewid(int reviewID) {
        this.reviewID = reviewID;
    }


    public void setGameid(int gameID) {
        this.gameID = gameID;
    }

}
