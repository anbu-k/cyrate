package com.example.cyrate.Logic;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.UserType;
import com.example.cyrate.models.UserModel;
import com.example.cyrate.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserLogic {

    public void getUser(getAllUsersResponse r) {
        String url = Const.GET_ALL_USERS_URL;
        List<UserModel> userModelList = new ArrayList<>();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("get users", "in onResponse");
                    JSONObject user = (JSONObject) response.get(0);
                    Log.d("get users", "created user");
                    UserModel newUser = new UserModel(user.get("email").toString(), user.get("userPass").toString());
                    Log.d("get users", "created user model");
                    userModelList.add(newUser);
                    Log.d("get users", "added to list");
                    r.onSuccess(userModelList);
                } catch (JSONException e) {
                    r.onError("OOF");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(arrayRequest);

    }

    /**
     * Makes a request to the server to post a new user
     * given some basic user info.
     */
    public void addUser(addUserResponse r, String userType, String email, String password) throws JSONException {

        String url = Const.POST_USER_URL;

        JSONObject newUserObject = new JSONObject();
        newUserObject.put("userType", userType);
        newUserObject.put("email", email);
        newUserObject.put("password", password);

        //not required for registration. default to empty. user can edit this in profile
        newUserObject.put("fullName", "");
        newUserObject.put("username", "");
        newUserObject.put("phoneNum", "");
        newUserObject.put("dob", "");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, newUserObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            r.onSuccess(response.toString());
                        } else {
                            r.onError("Null Response object received");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

//         newUserObjectRequest = new JsonObjectRequest(Request.Method.GET,
////                Const.URL_JSON_OBJECT, null,
//                Const.POST_USER_URL, null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        VolleyLog.d(response.toString());
//                        //we should have some sort of success/failure response
//                        try {
//                            if (response.get("success").toString().equals("false")){
//                                //log something on failure
//                                VolleyLog.d("failed to register user test");
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//             @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("Error: " + error.getMessage());
//            }
//        }) {
//             @Override
//             protected Map<String, String> getParams() {
//                 Map<String, String> params = new HashMap<String, String>();
//                 params.put("email", email);
//                 params.put("password", password);
//
//                 return params;
//             }
//         };

        //user requires email and password to register

        //add to queue
        AppController.getInstance().addToRequestQueue(request);
    }
}
