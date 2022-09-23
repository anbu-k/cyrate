package com.example.cyrate.models;

public class RestaurantListCardModel {
    String name;
    String category;
    String address;
    String rating;
    String hours;
    int img;

    public RestaurantListCardModel(String name, String category, String address, String rating, String hours, int img) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.rating = rating;
        this.hours = hours;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public String getHours() {
        return hours;
    }

    public int getImg() {
        return img;
    }


}
