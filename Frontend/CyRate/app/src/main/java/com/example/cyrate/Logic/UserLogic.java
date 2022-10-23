package com.example.cyrate.Logic;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.Logic.UserInterfaces.addUserResponse;
import com.example.cyrate.Logic.UserInterfaces.editProfileResponse;
import com.example.cyrate.Logic.UserInterfaces.getAllUsersResponse;
import com.example.cyrate.Logic.UserInterfaces.getEmailPasswordResponse;
import com.example.cyrate.Logic.UserInterfaces.getUserByEmailResponse;
import com.example.cyrate.Logic.UserInterfaces.getUsernamesResponse;
import com.example.cyrate.UserType;
import com.example.cyrate.activities.MainActivity;
import com.example.cyrate.models.UserModel;
import com.example.cyrate.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UserLogic {

    public static void getAllUsers(getAllUsersResponse r) {
        String url = Const.GET_ALL_USERS_URL;
        List<UserModel> userModelList = new ArrayList<>();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    //create a list of users
                    JSONObject user = (JSONObject) response.get(0);
                    UserModel newUser = new UserModel(user.get("email").toString(), user.get("userPass").toString());
                    userModelList.add(newUser);
                    r.onSuccess(userModelList);
                } catch (JSONException e) {
                    r.onError("OOF");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError("error response: " + error.toString());
            }
        });

        AppController.getInstance().addToRequestQueue(arrayRequest);

    }

    /**
     * make request to server to get a specific user's info given their email
     *
     * @param email
     * @param r
     */
    public void getUserByEmail(String email, getUserByEmailResponse r) {
        String url = Const.GET_USER_BY_EMAIL_URL + email;

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject userObject = (JSONObject) response;
                    Log.d("getUserByEmail response", userObject.toString());
                    UserModel user = convertToUserModel(userObject);
                    r.onSuccess(user);
                } catch (Exception e) {
                    r.onError("OOF");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError("OOF");
            }
        });

        AppController.getInstance().addToRequestQueue(objectRequest);

    }

    private UserModel convertToUserModel(JSONObject user) throws JSONException {
        UserModel newUserModel = new UserModel(user.get("email").toString(), user.get("userPass").toString());
        newUserModel.setUsername(user.get("username").toString());
        newUserModel.setUserType(convertUserType(user.get("userType").toString()));
        newUserModel.setFullName(user.get("realName").toString());
        newUserModel.setPhoneNum(user.get("phoneNum").toString());
        newUserModel.setDob(user.get("dob").toString());
        newUserModel.setPhotoUrl(user.get("photoUrl").toString());
        newUserModel.setUserId((int) user.get("userID"));

        return newUserModel;
    }

    private UserType convertUserType(String jsonType){
        switch (jsonType){
            case "guest":
                return UserType.GUEST;
            case "owner":
                return UserType.BUSINESS_OWNER;
            case "admin":
                return UserType.ADMIN;
            default:
                return UserType.BASIC_USER;
        }
    }

    /**
     * Makes a request to the server to post a new user
     * given some basic user info.
     */
    public void addUser(addUserResponse r, String userType, String email, String password,
                        String username, String phoneNum, String dateOfBirth) throws JSONException {

        String url = Const.POST_USER_URL;

        JSONObject newUserObject = new JSONObject();
        newUserObject.put("userType", userType);
        newUserObject.put("email", email);
        newUserObject.put("userPass", password);
        newUserObject.put("username", username);


        //not required for registration. default to empty. user can edit this in profile
        newUserObject.put("realName", "");
        //TODO
        //need to add a username field in registration page.
        //if a user updates their username from edit profile to the email of a future user there will be problems
        newUserObject.put("phoneNum", phoneNum);
        newUserObject.put("dob", dateOfBirth);
        newUserObject.put("photoUrl", "https://sumaleeboxinggym.com/wp-content/uploads/2018/06/Generic-Profile-1600x1600.png");

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

        //add to queue
        AppController.getInstance().addToRequestQueue(request);
    }

    public void getAllEmailPassword(getEmailPasswordResponse r) {
        String url = Const.GET_ALL_USERS_URL;
        HashMap<String, String> emailPasswordMap = new HashMap<>();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject user = (JSONObject) response.get(i);
                        emailPasswordMap.put(user.get("email").toString(), user.get("userPass").toString());
                    }
                    r.onSuccess(emailPasswordMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError("error response: " + error.toString());
            }
        });

        AppController.getInstance().addToRequestQueue(arrayRequest);

    }

    public void getAllUsernames(getUsernamesResponse r) {
        String url = Const.GET_ALL_USERS_URL;
        HashSet<String> usernameMap = new HashSet<>();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject user = (JSONObject) response.get(i);
                        usernameMap.add(user.get("username").toString());
                    }
                    r.onSuccess(usernameMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError("error response: " + error.toString());
            }
        });

        AppController.getInstance().addToRequestQueue(arrayRequest);

    }

    public void getAllPhoneNumbers(getUsernamesResponse r) {
        String url = Const.GET_ALL_USERS_URL;
        HashSet<String> phoneNumberSet = new HashSet<>();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject user = (JSONObject) response.get(i);
                        phoneNumberSet.add(user.get("phoneNum").toString());
                    }
                    r.onSuccess(phoneNumberSet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError("error response: " + error.toString());
            }
        });

        AppController.getInstance().addToRequestQueue(arrayRequest);

    }

    public void editUser(int id, String username, String email, String password, String name, String dob, String photo, String phoneNum, editProfileResponse r) throws JSONException {
        String url = Const.EDIT_USER_URL + String.valueOf(id);

        JSONObject userObject = new JSONObject();
        userObject.put("userType", MainActivity.globalUser.getUserType().toString());
        userObject.put("realName", name);
        userObject.put("username", username);
        userObject.put("userPass", password);
        userObject.put("email", email);
        userObject.put("phoneNum", phoneNum);
        userObject.put("dob", dob);
        userObject.put("photoUrl", photo);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, userObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                r.onSuccess("Succesfully updated profile!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError(error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(request);

    }
}
