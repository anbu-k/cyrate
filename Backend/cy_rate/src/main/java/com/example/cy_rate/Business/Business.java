package com.example.cy_rate.Business;

import java.util.ArrayList;
import com.example.cy_rate.Review.Review; //review class

public class Business {
    
    //----Business information-----//
    private int bus_id;
    private String bus_name;
    private String bus_type;
    private String photo_url;
    private String hours;
    private String location;
    private int owner_id;
    private String menu_link;
    private String price_gauge;
    
    //-------review's stuff--------//
    private int review_count;
    private int review_sum;
    private ArrayList<Review> review_list;
    
    public Business(int bus_id, String bus_type, String photo_url, String hours, String location, 
                    int owner_id, String menu_link, String price_gauge, int review_count, int review_sum){
        this.bus_id = bus_id;
        this.bus_type = bus_type;
        this.photo_url = photo_url;
        this.hours = hours;
        this.location = location;
        this.owner_id = owner_id;
        this.menu_link = menu_link;
        this.price_gauge = price_gauge;
        this.review_count = review_count;
        this.review_sum = review_sum;
    }

    //---------- Getter Setter's ----------// 
    public String get_name()
    {
        return bus_name;
    }
    
    public void set_name(String bname)
    {
        this.bus_name = bname;
    }
    
    public int get_id(){
        return bus_id;
    }

    public void set_id(int bus_id){
        this.bus_id = bus_id;
    }

    public String get_type(){
        return bus_type;
    }

    public void set_type(String type){
        this.bus_type = type;
    }

    public String get_url()
    {
        return photo_url;
    }

    public void set_url(String url)
    {
        this.photo_url = url;
    }

    public String get_hours()
    {
        return hours;
    }

    public void set_hours(String hours)
    {
        this.hours = hours;
    }

    public String get_location()
    {
        return location;
    }

    public void set_location(String location)
    {
        this.location = location;
    }

    public int get_ownerID()
    {
        return owner_id;
    }

    public void set_owner(int id)
    {
        this.owner_id = id;
    }

    public String get_menu()
    {
        return menu_link;
    }

    public void set_menu(String link)
    {
        this.menu_link = link;
    }

    public String get_priceEst()
    {
        return price_gauge;
    }

    public void set_priceEst(String est)
    {
        this.price_gauge = est;
    }

    public int get_review_count()
    {
        return review_count;
    }


    public void set_review_count(int count)
    {
        this.review_count = count;
    }

    public int get_review_sum()
    {
        return review_sum;
    }

    public void set_review_sum(int sum)
    {
        this.review_sum = sum;
    }


    //---------- Utility --------------//
    public void add_review(int rating)
    {
        review_sum += rating;
        review_count++;
    }

    @Override
    public String toString()
    {
        return "";
    }



}
