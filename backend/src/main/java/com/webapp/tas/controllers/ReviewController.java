package com.webapp.tas.controllers;

import com.webapp.tas.objects.Review;
import com.webapp.tas.tables.records.ReviewsRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.webapp.tas.Tables.REVIEWS;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private DSLContext jooq;

    @GetMapping("/reviews")
    public List<Review> reviewList(){
        return jooq.select().from(REVIEWS).fetchInto(Review.class);
    }

    /**
     * ReviewID validation methon needs to be added
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
