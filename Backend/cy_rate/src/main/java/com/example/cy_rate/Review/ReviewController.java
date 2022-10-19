package com.example.cy_rate.Review;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Sample Review Object
 * {
 * "business": 1,
 * "user": 7,
 * "rateVal": 4,
 * "reviewTxt": "good food, bad service"
 * }
 */
@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepo;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    /**
     * 
     * @return all reviews in database table
     */
    @GetMapping(path="/reviews/all")
    List<Review> getAllReviews()
    {
        return reviewRepo.findAll();
    }

    /**
     * @param userId - the users id
     * @return Get All reviews from a specific user
     */
    @GetMapping(path="/reviews/user/{userId}")
    List<Review> getUserReviews(@PathVariable int userId)
    {
        return reviewRepo.findByUser(userId);
    }

    /**
     * @param busId - businesses id 
     * @return Get All reviews for a specific business
     */
    @GetMapping(path="/reviews/business/{busId}")
    List<Review> getBusinessReviews(@PathVariable int busId)
    {
        return reviewRepo.findByBusiness(busId);
    }


    @PostMapping(path="/reviews/create")
    String createReview(@RequestBody Review review){
        if(review == null)
            return failure;
        
        reviewRepo.save(review);
        return success;
    }
       
}
