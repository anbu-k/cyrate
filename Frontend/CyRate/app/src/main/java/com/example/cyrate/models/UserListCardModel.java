package com.example.cyrate.models;

import com.example.cyrate.UserType;

public class UserListCardModel {
    private int userId;
    private UserType userType;
    private String email;
    private String password;
    private String fullName;
    private String username;
    private String phoneNum;
    private String dob;
    private String photoUrl;

    public UserListCardModel(int userId, UserType userType, String email, String password, String fullName,
                             String username, String phoneNum, String dob, String photoUrl){
        this.userId = userId;
        this.userType = UserType.BASIC_USER;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.username = username;
        this.phoneNum = phoneNum;
        this.dob = dob;
        this.photoUrl = "";
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getProfilePic() {
        return photoUrl;
    }
}
