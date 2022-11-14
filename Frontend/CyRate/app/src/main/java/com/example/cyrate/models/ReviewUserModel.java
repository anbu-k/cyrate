package com.example.cyrate.models;

public class ReviewUserModel {
    private int userId;
    private String fullName;
    private String username;
    private String photoUrl;

    /**
     * Model class to hold only the required User info needed for Reviews.
     * Prevents unnecessary data from being passed.
     *
     * @param userId
     * @param fullName
     * @param username
     * @param photoUrl
     */
    public ReviewUserModel(int userId, String fullName, String username, String photoUrl) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.photoUrl = photoUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
