package com.example.cy_rate.User;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cy_rate.Review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
//@Hidden
import io.swagger.v3.oas.annotations.Hidden;

@Entity
public class User {
    // pk 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private int userId;
    private String userType;
    private String realName;
    private String username;
    private String userPass;
    private String email;
    private String phoneNum;
    private String dob;
    private String resetPassword;
    private String profilePicture;
    
    //maps users to reviews
    // @OneToMany(mappedBy = "user")
    // private List<Review> reviews;
    
    public User(){

    }

    public User(String userType, String realName, String username,String userPass, String email, String phoneNum, String dob, String profilePicture) //String newPassword
    {
        this.userType = userType;
        this.realName = realName;
        this.username = username;
        this.userPass = userPass;
        this.email = email;
        this.phoneNum = phoneNum;
        this.dob = dob;
        this.profilePicture = profilePicture;
        // this.resetPassword = resetPassword;
    }

    // getters & setters
    
    public int getuserID()
    {
        return userId;
    }

    public void setuserID(int uId)
    {
        this.userId = uId;
    }

    public String getuserType()
    {
        return userType;
    }

    public void setuserType(String uType)
    {
        this.userType = uType;
    }

    public String getrealName()
    {
        return realName;
    }

    public void setrealName(String rName)
    {
        this.realName = rName;
    }

    public String getusername()
    {
        return username;
    }

    public void setusername(String uname)
    {
        this.username = uname;
    }

    public String getuserPass()
    {
        return userPass;
    }

    public void setuserPass(String uPass)
    {
        this.userPass = uPass;
    }

    public String getemail()
    {
        return email;
    }

    public void setemail(String email)
    {
        this.email = email;
    }

    public String getphoneNum()
    {
        return phoneNum;
    }

    public void setphoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public String getdob()
    {
        return dob;
    }

    public void setdob(String dob)
    {
        this.dob = dob;
    }

    // public String getnewPassword()
    // {
    //     return resetPassword;
    // }

    // public void setNewPassword(String resetPassword)
    // {
    //     this.resetPassword = resetPassword;
    // }

    public String getPhotoUrl()
    {
        return profilePicture;
    }

    public void setPhotoUrl(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    // Review Class Getter and setters for one to many relation
    // public void addReview(Review review)
    // {
    //     this.reviews.add(review);
    // }   

    // public List<Review> getReviews()
    // {
    //     return reviews;
    // }

    // public void setReviews(List<Review> givenReviews)
    // {
    //     reviews = givenReviews;
    // }
    

    @Override
    public String toString()
    {
        return userId + "\n" + userType + "\n" 
        + realName + "\n" + username + "\n" 
        + userPass + "\n" + email + "\n"
        + phoneNum + "\n" + dob + "\n"
        ;
    }

    
}
