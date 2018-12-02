package com.webapp.tas.objects;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;


public @Data class RankingDto {

    double score;
    String title;
    int gameid;


    public void setScore(double score) {
        BigDecimal bd = new BigDecimal(score).setScale(2, RoundingMode.FLOOR);
        this.score = bd.doubleValue() * 10;
    }

}
