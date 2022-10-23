package com.example.cyrate;


public enum UserType {
    GUEST,              //guest
    BASIC_USER,         //normal
    BUSINESS_OWNER,
    ADMIN;

    @Override
    public String toString(){
        String s;
        switch (this){
            case GUEST:
                s = "guest";
                break;
            case BUSINESS_OWNER:
                s = "owner";
                break;
            case ADMIN:
                s = "admin";
                break;
            case BASIC_USER:
            default:
                s = "normal";
                break;
        }
        return s;
    }
}


