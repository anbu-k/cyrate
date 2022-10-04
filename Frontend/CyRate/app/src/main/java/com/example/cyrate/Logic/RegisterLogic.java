package com.example.cyrate.Logic;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cyrate.models.UserModel;
import com.example.cyrate.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterLogic {

    /**
     * Makes a request to the server to post a new user
     * given some basic user info.
     */
    public void registerUser(getRegisterResponse r, UserModel newUser) throws JSONException {
        JsonObjectRequest newUserObjectRequest;

         newUserObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                Const.URL_JSON_OBJECT, null,
                Const.POST_USER_URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(response.toString());
                        //we should have some sort of success/failure response
                        if (response.toString().equals("failure")){
                            //log something on failure
                            VolleyLog.d("failed to register user %s", newUser.getUserId());
                        }
                    }
                }, new Response.ErrorListener() {

             @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        });

        //user requires email and password to register
    }
}
