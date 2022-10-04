package com.example.cyrate.Logic;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cyrate.AppController;
import com.example.cyrate.models.UserModel;
import com.example.cyrate.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                        try {
                            if (response.get("success").toString().equals("false")){
                                //log something on failure
                                VolleyLog.d("failed to register user %s", newUser.getUserId());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

             @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        }) {
             @Override
             protected Map<String, String> getParams() {
                 Map<String, String> params = new HashMap<String, String>();
                 params.put("email", newUser.getEmail());
                 params.put("password", newUser.getPassword());

                 return params;
             }
         };

        //user requires email and password to register

        //add to queue
        AppController.getInstance().addToRequestQueue(newUserObjectRequest);
    }
}
