package com.example.cyrate.models;

import com.example.cyrate.UserType;

public class UserModel {
    private int userId;
    private UserType userType;
    private String email;
    private String password;

    public UserModel(int userId, UserType userType, String email, String password){
        this.userId = userId;
        this.userType = userType;
        this.email = email;
        this.password = password;
    }
    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public UserType getUserType() {return userType;}

    public void setUserType(UserType userType) {this.userType = userType;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

}

