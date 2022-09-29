package com.example.cy_rate.Business;

import java.util.ArrayList;
import com.example.cy_rate.Review.Review; //review class


// JPA stuff
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Business {
    
    //----Business information-----//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int busId;
    private String busName;
    private String busType;
    private String photoUrl;
    private String hours;
    private String location;
    private int ownerId;
    private String menuLink;
    private String priceGauge;
    
    //-------review's stuff--------//

    private int reviewSum;
    private int reviewCount;
    
    public Business()
    {
        /* 
        this.busType = "";
        this.busName = "";
        this.photoUrl = "";
        this.hours = "";
        this.location = "";
        this.ownerId = 0;
        this.menuLink = "";
        this.priceGauge = "";
        this.reviewCount = 0;
        this.reviewSum = 0;
        */
    }

    public Business(String busType, String busName, String photoUrl, String hours, String location, int ownerId, String menuLink, String priceGauge, int reviewCount, int reviewSum){
        this.busType = busType;
        this.busName = busName;
        this.photoUrl = photoUrl;
        this.hours = hours;
        this.location = location;
        this.ownerId = ownerId;
        this.menuLink = menuLink;
        this.priceGauge = priceGauge;
        this.reviewCount = reviewCount;
        this.reviewSum = reviewSum;
    }

    //---------- Getter Setter's ----------// 
    public String getBusName()
    {
        return busName;
    }
    
    public void setBusName(String bname)
    {
        this.busName = bname;
    }
    
    public int getBusId(){
        return busId;
    }

    public void setBusId(int busId){
        this.busId = busId;
    }

    public String getBusType(){
        return busType;
    }

    public void setBusType(String type){
        this.busType = type;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }

    public void setPhotoUrl(String url)
    {
        this.photoUrl = url;
    }

    public String getHours()
    {
        return hours;
    }

    public void setHours(String hours)
    {
        this.hours = hours;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(int id)
    {
        this.ownerId = id;
    }

    public String getMenuLink()
    {
        return menuLink;
    }

    public void setMenuLink(String link)
    {
        this.menuLink = link;
    }

    public String getPriceGauge()
    {
        return priceGauge;
    }

    public void setPriceGauge(String est)
    {
        this.priceGauge = est;
    }

    public int getReviewCount()
    {
        return reviewCount;
    }

    public void setReviewCount(int count)
    {
        this.reviewCount = count;
    }

    public int getReviewSum()
    {
        return reviewSum;
    }

    public void setReviewSum(int sum)
    {
        this.reviewSum = sum;
    }

    public int reviewAVG()
    {
        //avoid dividing by 0
        if(reviewCount == 0)
            return 0;
        return reviewSum / reviewCount;
    }

    //---------- Utility --------------//
    public void add_review(Review review)
    {
        this.reviewSum += review.getRateVal();
        this.reviewCount++;
    }

    @Override
    public String toString()
    {
        return busId + "\n" + busName + "\n" + busType 
        + "\n" + hours + "\n" + location + "\n" + priceGauge;
    }



}
