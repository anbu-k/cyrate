package com.example.cyrate.models;

import com.example.cyrate.UserType;

public class UserModel {
    private int userId;
    private UserType userType;
    private String email;
    private String password;
    private String fullName;
    private String username;
    private String phoneNum;
    private String dob;
    private String photoUrl;

    public UserModel(String email, String password){
        //default to basic user
        this.userType = UserType.BASIC_USER;
        this.email = email;
        this.password = password;
        this.photoUrl = "";
    }
    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public UserType getUserType() {return userType;}

    public void setUserType(UserType userType) {this.userType = userType;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFullName(){return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getUsername(){return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPhoneNum() {return phoneNum;}

    public void setPhoneNum(String phoneNum) {this.phoneNum = phoneNum;}

    public String getDob() {return dob;}

    public void setDob(String dob) {this.dob = dob;}

    public String getPhotoUrl(){return photoUrl;}

    public void setPhotoUrl(String url) {this.photoUrl = url;}

}

