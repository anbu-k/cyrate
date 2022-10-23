package com.example.cy_rate.Review;
import com.example.cy_rate.Business.Business;
import com.example.cy_rate.Business.BusinessRepository;
import com.example.cy_rate.User.User;
import com.example.cy_rate.User.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;

// JPA stuff
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;


/**
 * figure down the line will have these connected to an actual resturant object
 * each resturant object will have a list of ratings/reviews connected to it
 * 
 * For now just messing around with springboot
 */
@Entity
@Table(name = "Reviews")
public class Review {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden //hides id in swagger docs
    @Column(name="rid")
    private int rid;

    @Column(name= "rateVal")
    private int rateVal;

    @Column(name = "reviewTxt")
    private String reviewTxt;

    @ManyToOne //(fetch = FetchType.LAZY)
    //@JsonIgnore
    @JoinColumn(name = "bid", referencedColumnName = "busId")
    private Business business;

    @ManyToOne //(fetch = FetchType.LAZY)
    //@JsonIgnore
    @JoinColumn(name = "uid", referencedColumnName = "userId")
    private User user;


    public Review(){
        this.rateVal = 0;
        this.reviewTxt = "";
    }

    public Review(int rateVal, String reviewTxt){
        this.rateVal = rateVal;
        this.reviewTxt = reviewTxt;
    }


    //---------------  Getter & Setters-------------//
    public int getRid()
    {
        return rid;
    }

    public void setRid(int rid)
    {
        this.rid = rid;
    }

    public Business getBusiness()
    {
        return business;
    }


    public void setBusiness(Business bus)
    {
        this.business = bus;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }


    public int getRateVal(){
        return this.rateVal;
    }

    public void setRateVal(int givenRating){
        this.rateVal = givenRating;
    }

    public String getReviewTxt(){
        return this.reviewTxt;
    }
    
    public void setReviewTxt(String review){
        this.reviewTxt = review;
    }

    //Sam DeFrancisco's review for Potbelly 
    //rating...
    @Override
    public String toString() {
        return user.getrealName() + "'s review for " + business.getBusName() + "\nRating out of 5: " + rateVal + "\nReview: " + reviewTxt;
    }
    
}


