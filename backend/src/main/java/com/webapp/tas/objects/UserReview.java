package com.webapp.tas.objects;

import lombok.Data;

public @Data class UserReview {
    int reviewID;
    String title;
    int rate;
    String gameTitle;


    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewid(int reviewID) {
        this.reviewID = reviewID;
    }

}
