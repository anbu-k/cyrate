package com.example.cyrate.models;

public class ReviewListCardModel {
    private String reviewerName;
    private String review;
    int image;

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ReviewListCardModel(String reviewerName, String review, int image) {
        this.reviewerName = reviewerName;
        this.review = review;
        this.image = image;
    }
}