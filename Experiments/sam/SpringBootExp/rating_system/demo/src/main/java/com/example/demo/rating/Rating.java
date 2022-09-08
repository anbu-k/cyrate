package com.example.demo.rating;

/**
 * figure down the line will have these connected to an actual resturant object
 * each resturant object will have a list of ratings/reviews connected to it
 * 
 * For now just messing around with springboot
 */

public class Rating {

    private String restName; //will prob be Rest obj
    private double rateVal;
    private String reviewTxt;

    public Rating(){
        this.restName = "";
        this.rateVal = 0.0;
        this.reviewTxt = "";
    }

    public String getRestName(){
        return this.restName;
    }
    
    public void setRestName(String givenName){
        this.restName = givenName;
    }

    public double getRateVal(){
        return this.rateVal;
    }

    public void setRateVal(double givenRating){
        this.rateVal = givenRating;
    }

    public String getReviewTxt(){
        return this.restName;
    }
    
    public void setReviewTxt(String review){
        this.reviewTxt = review;
    }


    @Override
    public String toString() {
        return restName + "\nRating out of 5: " + rateVal + "\nReview: " + reviewTxt;
    }
    
}

