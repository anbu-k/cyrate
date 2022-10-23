package com.example.cyrate.models;

public class ReviewListCardModel {
    private int reviewId;
    private int rateVal;
    private String reviewText;
    private int businessId;
    private int reviewUserId;

    public ReviewListCardModel(int reviewId, int rateVal, String reviewText, int businessId, int reviewUserId) {
        this.reviewId = reviewId;
        this.rateVal = rateVal;
        this.reviewText = reviewText;
        this.businessId = businessId;
        this.reviewUserId = reviewUserId;
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

    public int getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(int reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    @Override
    public String toString() {
        return "ReviewListCardModel{" +
                "reviewId=" + reviewId +
                ", rateVal=" + rateVal +
                ", reviewText='" + reviewText + '\'' +
                ", businessId=" + businessId +
                ", reviewUserId=" + reviewUserId +
                '}';
    }
}