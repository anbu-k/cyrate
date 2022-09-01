package com.cs309.demo.restaurant;

public class Restaurant {
    private long id;
    private String name;
    private Integer rating;

    public Restaurant(long id, String name, Integer rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
