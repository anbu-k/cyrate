package com.example.cyrate.Logic;

import com.example.cyrate.models.UserModel;

import java.util.HashMap;

public interface getUsernamePasswordResponse {
    public void onSuccess(HashMap<String, String> usernamePassword);
    public void onError(String s);
}
