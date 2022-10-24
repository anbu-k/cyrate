package com.example.cy_rate.Review;
import java.util.List;

import com.example.cy_rate.Business.Business;
import com.example.cy_rate.User.User;
import com.example.cy_rate.Business.BusinessRepository;
import com.example.cy_rate.User.UserRepository;

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
    BusinessRepository businessRepo;

    @Autowired
    UserRepository userRepo;

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
     * 
     * @param bid specific business id
     * @return all reviews for a specific business
     */
    @GetMapping(path="/reviews/business/{bid}")
    List<Review> getReviewsByBusiness(@PathVariable int bid)
    {
        Business b = businessRepo.findById(bid);
        return reviewRepo.findByBusiness(b);
    }
    
    /**
     *  
     * @param uid the user id
     * @return all reviews for a specific user
     */
    @GetMapping(path="/reviews/user/{uid}")
    List<Review> getReviewsByUser(@PathVariable int uid)
    {
        User u = userRepo.findById(uid);
        return reviewRepo.findByUser(u);
    }

    /**
     * 
     * @param bid - business id for review
     * @param uid - user id that is leaving review
     * @param review - Review Object {"rateVal": int, "reviewTxt": Str}
     * @return suc/fail string
     */
    @PostMapping(path="/review/{bid}/user/{uid}/createReview")
    String createReview(@PathVariable int bid, @PathVariable int uid, @RequestBody Review review)
    {
        try {
            // Get user and business related to review
            Business b = businessRepo.findById(bid);
            User u = userRepo.findById(uid);
    
            // Set reviews business and user to ones found above
            review.setBusiness(b);
            review.setUser(u);
    
            // Add review to List<Reviews> within user and business objects
            // b.addReview(review);
            // u.addReview(review);
            
            // Save all changes
            reviewRepo.save(review);
            // businessRepo.save(b);
            // userRepo.save(u);
            
            return success;
        } catch (Exception e) {
            return e.toString();
        }
    }
       
}
