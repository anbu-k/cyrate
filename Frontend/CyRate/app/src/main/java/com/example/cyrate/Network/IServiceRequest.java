package com.example.cyrate.Network;

import com.example.cyrate.Logic.IVolleyListener;

import org.json.JSONObject;

public interface IServiceRequest {
    public void sendToServer(String url, JSONObject newUserObj, String methodType);
    public void addVolleyListener(IVolleyListener logic);
}
