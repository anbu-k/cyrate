package com.example.cyrate.models;

public class ReviewListCardModel {
    private int reviewId;
    private int rateVal;
    private String reviewText;
    private int businessId;
    private ReviewUserModel reviewUser;

    public ReviewListCardModel(int reviewId, int rateVal, String reviewText, int businessId, ReviewUserModel reviewUser) {
        this.reviewId = reviewId;
        this.rateVal = rateVal;
        this.reviewText = reviewText;
        this.businessId = businessId;
        this.reviewUser = reviewUser;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getRateVal() {
        return rateVal;
    }

    public void setRateVal(int rateVal) {
        this.rateVal = rateVal;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public ReviewUserModel getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(ReviewUserModel reviewUser) {
        this.reviewUser = reviewUser;
    }

    @Override
    public String toString() {
        return "ReviewListCardModel{" +
                "reviewId=" + reviewId +
                ", rateVal=" + rateVal +
                ", reviewText='" + reviewText + '\'' +
                ", businessId=" + businessId +
                ", reviewUserId=" + reviewUser +
                '}';
    }
}