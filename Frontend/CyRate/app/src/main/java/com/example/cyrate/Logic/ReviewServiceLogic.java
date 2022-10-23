package com.example.cyrate.Logic;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.Logic.BusinessInterfaces.getBusinessByIDResponse;
import com.example.cyrate.Logic.ReviewInterfaces.getReviewsResponse;
import com.example.cyrate.Logic.ReviewInterfaces.reviewStringResponse;
import com.example.cyrate.activities.MainActivity;
import com.example.cyrate.models.BusinessListCardModel;
import com.example.cyrate.models.ReviewListCardModel;
import com.example.cyrate.models.ReviewUserModel;
import com.example.cyrate.models.UserModel;
import com.example.cyrate.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReviewServiceLogic {

    public void getReviews(int busId, getReviewsResponse r){
        List<ReviewListCardModel> reviewModelsList = new ArrayList<>();
        String url = Const.GET_REVIEWS_BY_BUS_ID + String.valueOf(busId);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("REVIEWS", response.toString());
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject review = (JSONObject) response.get(i);
                        Log.d("REVIEW JSON OBJ", review.toString());

                        JSONObject reviewUserJSON = review.getJSONObject("user");
                        ReviewUserModel reviewUserModel = new ReviewUserModel(
                            reviewUserJSON.getInt("userID"),
                            reviewUserJSON.getString("realName"),
                            reviewUserJSON.getString("username"),
                            reviewUserJSON.getString("photoUrl")
                        );

                        ReviewListCardModel reviewListCardModel = new ReviewListCardModel(
                            review.getInt("rid"),
                            review.getInt("rateVal"),
                            review.getString("reviewTxt"),
                            review.getJSONObject("business").getInt("busId"),
                            reviewUserModel
                        );

                        reviewModelsList.add(reviewListCardModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                r.onSuccess(reviewModelsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError(error.toString());
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

    public void addReview(int busId, int userId, String reviewTxt, int ratingVal, reviewStringResponse r) throws JSONException {
        String url = "http://coms-309-020.class.las.iastate.edu:8080/review/" + String.valueOf(busId) + "/user/" + String.valueOf(userId) + "/createReview/";

        Log.d("ADD REVIEW URL", url);

        BusinessServiceLogic businessServiceLogic = new BusinessServiceLogic();

        businessServiceLogic.getBusinessesById(busId, new getBusinessByIDResponse() {
            @Override
            public void onSuccess(BusinessListCardModel business) {
                JSONObject user = new JSONObject();
                JSONObject bus = new JSONObject();
                try {
                    user.put("userType", MainActivity.globalUser.getUserType());
                    user.put("realName", MainActivity.globalUser.getFullName());
                    user.put("username", MainActivity.globalUser.getUsername());
                    user.put("photoUrl", MainActivity.globalUser.getPhotoUrl());
                    user.put("userPass", MainActivity.globalUser.getPassword());
                    user.put("email", MainActivity.globalUser.getEmail());
                    user.put("phoneNum", MainActivity.globalUser.getPhoneNum());
                    user.put("dob", MainActivity.globalUser.getDob());
                    user.put("userID", MainActivity.globalUser.getUserId());

                    bus.put("busName", business.getBusName());
                    bus.put("busType", business.getBusType());
                    bus.put("photoUrl", business.getPhotoUrl());
                    bus.put("hours", business.getHours());
                    bus.put("location", business.getLocation());
                    bus.put("busName", business.getBusName());
                    bus.put("ownerId", -1);
                    bus.put("menuLink", "");
                    bus.put("priceGauge", business.getPriceGauge());
                    bus.put("reviewSum", business.getReviewSum());
                    bus.put("reviewCount", business.getReviewCount());

                    JSONObject newReview = new JSONObject();
                    newReview.put("reviewTxt", reviewTxt);
                    newReview.put("rateVal", ratingVal);
                    newReview.put("user", user);
                    newReview.put("business", bus);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, newReview,
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
                                    Log.d("ADD REVIEW ERROR", error.toString());
                                    r.onError(error.getMessage());
                                }
                            }
                    );

                    AppController.getInstance().addToRequestQueue(request);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String s) {
                r.onError(s);
            }
        });


    }
}
