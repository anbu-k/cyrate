package com.example.cyrate.models;

public class BusinessPostCardModel {
    private int postId;
    private String postTxt;
    private String date;
    private String photoUrl;
    private BusinessListCardModel business;

    public BusinessPostCardModel(int postId, String postTxt, String date, String photoUrl, BusinessListCardModel business) {
        this.postId = postId;
        this.postTxt = postTxt;
        this.date = date;
        this.photoUrl = photoUrl;
        this.business = business;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTxt() {
        return postTxt;
    }

    public void setPostTxt(String postTxt) {
        this.postTxt = postTxt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public BusinessListCardModel getBusiness() {
        return business;
    }

    public void setBusiness(BusinessListCardModel business) {
        this.business = business;
    }
}
