package com.webapp.tas.controllers;

import com.webapp.tas.objects.Review;
import com.webapp.tas.tables.records.GamesRecord;
import com.webapp.tas.tables.records.ReviewsRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.webapp.tas.Tables.GAMES;
import static com.webapp.tas.Tables.REVIEWS;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private DSLContext jooq;

    /**
     * Returns list of all reviews in DB
     * @return
     */
    @GetMapping("/reviews")
    public List<Review> reviewList(){
        return jooq.select().from(REVIEWS).fetchInto(Review.class);
    }

    /**
     * Returns list of reviews for given gameid.
     * @param gID
     * @return
     */
    @GetMapping("/reviews/{gID}")
    public List<Review> gameReviewsList(@PathVariable int gID){
        return jooq.select().from(REVIEWS).where(REVIEWS.GAMEID.eq(gID)).fetchInto(Review.class);
    }


    /**
     * GameID needs to be taken from game object instance on frontend side
     * ReviewID validation method needs to be added
     * @param newReview
     */
    @PostMapping("/addreview")
    public void addReview(@RequestBody Review newReview){
        ReviewsRecord review = jooq.newRecord(REVIEWS);
        review.setReviewid(newReview.getReviewID());
        review.setTitle(newReview.getTitle());
        review.setContent(newReview.getContent());
        review.setRate(newReview.getRate());
        review.setUserid(newReview.getUserID());
        review.setGameid(newReview.getGameID());
        review.store();
    }
    //TODO pobieranie nazwy uzytkownika
}
