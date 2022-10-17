package com.example.cy_rate.Review;
import com.example.cy_rate.Business.Business;

// JPA stuff
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;


/**
 * figure down the line will have these connected to an actual resturant object
 * each resturant object will have a list of ratings/reviews connected to it
 * 
 * For now just messing around with springboot
 */
@Entity
@Table(name = "Reviews")
public class Review {
    
   //testing ci 'only'
   //test 2
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int review_id;

    @Column(name= "rateVal")
    private int rateVal;

    @Column(name = "reviewTxt")
    private String reviewTxt;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Business business;

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


    //---------------  Getter & Setters-------------//
    public int get_id(){
        return review_id;
    }

    public void set_id(int id){
        this.review_id = id;
    }
    public String getRestName(){
        return this.business.getBusName();
    }
    
    public void setRestName(String givenName){
        this.business.setBusName(givenName);
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
        return business.getBusName() + "\nRating out of 5: " + rateVal + "\nReview: " + reviewTxt;
    }
    
}


