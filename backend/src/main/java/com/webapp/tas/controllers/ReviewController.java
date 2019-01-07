package com.webapp.tas.controllers;

import com.webapp.tas.errors.NotFoundException;
import com.webapp.tas.errors.NotUniqueExcetpion;
import com.webapp.tas.objects.Review;
import com.webapp.tas.objects.ReviewShort;
import com.webapp.tas.objects.UserReview;
import com.webapp.tas.tables.records.GamesRecord;
import com.webapp.tas.tables.records.ReviewsRecord;
import com.webapp.tas.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.webapp.tas.Tables.*;
import static com.webapp.tas.Tables.USERS;

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
    public HttpStatus addReview(@RequestBody ReviewShort newReview){
        ReviewsRecord review = jooq.newRecord(REVIEWS);
        UsersRecord ur = jooq.fetchOne(USERS, USERS.LOGIN.eq(newReview.getUserID()));
        GamesRecord gr = jooq.fetchOne(GAMES, GAMES.GAMEID.eq(newReview.getGameID()));

        if (ur == null) throw new NotFoundException("User does not exist");
        if (gr == null) throw new NotFoundException("Game does not exist");

        //review.setReviewid(newReview.getReviewID());
        review.setTitle(newReview.getTitle());
        review.setContent(newReview.getContent());
        review.setRate(newReview.getRate());
        review.setUserid(newReview.getUserID());
        review.setGameid(newReview.getGameID());
        try{
            review.store();
        }catch (Exception e){
            throw new NotUniqueExcetpion("ReviewID is not unique");
        }
        return HttpStatus.CREATED;
    }

}
