package com.webapp.tas.controllers;

import com.webapp.tas.objects.Review;
import com.webapp.tas.objects.UserReview;
import com.webapp.tas.tables.records.ReviewsRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.webapp.tas.Tables.GAMES;
import static com.webapp.tas.Tables.REVIEWS;
import static org.jooq.impl.DSL.*;

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

//    public String reviewList(){
//        return jooq.select().from(REVIEWS).fetch()
//                .formatJSON();
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
     * returns a list of UserReview for given userID
     * @param uID
     * @return
     */
    @GetMapping("/reviews/user/{uID}")
    public List<UserReview> getUserReviews(@PathVariable String uID){
        return jooq.select(REVIEWS.REVIEWID, REVIEWS.TITLE, REVIEWS.RATE, (GAMES.TITLE).as("gameTitle"))
                .from(REVIEWS)
                .join(GAMES).on(GAMES.GAMEID.eq(REVIEWS.GAMEID))
                .where(REVIEWS.USERID.eq(uID))
                .groupBy(REVIEWS.REVIEWID, REVIEWS.TITLE, REVIEWS.RATE, GAMES.TITLE)
                .fetchInto(UserReview.class);
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
}
