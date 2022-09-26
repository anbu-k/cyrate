package com.example.cy_rate.Review;
import com.example.cy_rate.Business.Business;
/**
 * figure down the line will have these connected to an actual resturant object
 * each resturant object will have a list of ratings/reviews connected to it
 * 
 * For now just messing around with springboot
 */

public class Review {

    private Business business;
    private int rateVal;
    private String reviewTxt;

    public Review(){
        this.business = null;
        this.rateVal = 0;
        this.reviewTxt = "";
    }

    public Review(Business business, int rateVal, String reviewTxt){
        this.business = business;
        this.rateVal = rateVal;
        this.reviewTxt = reviewTxt;
    }

    public String getRestName(){
        return this.business.get_name();
    }
    
    public void setRestName(String givenName){
        this.business.set_name(givenName);
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


    @Override
    public String toString() {
        return business.get_name() + "\nRating out of 5: " + rateVal + "\nReview: " + reviewTxt;
    }
    
}


