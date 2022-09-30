package com.example.cy_rate.User;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String userType;
    private String realName;
    private String username;
    private String userPass;
    private String email;
    private String phoneNum;
    private String dob;

    public User(){

    }

    public User(int userID, String userType, String realName, String username,String userPass, String email, String phoneNum, String dob)
    {
        this.userID = userID;
        this.userType = userType;
        this.realName = realName;
        this.username = username;
        this.userPass = userPass;
        this.email = email;
        this.phoneNum = phoneNum;
        this.dob = dob;
    }

    // getters & setters
    
    public int getuserID()
    {
        return userID;
    }

    public void setuserID(int uID)
    {
        this.userID = uID;
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


    
}
