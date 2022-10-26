package com.example.cyrate.Logic;

import android.annotation.SuppressLint;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.Logic.BusinessInterfaces.businessStringResponse;
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
import java.util.HashMap;
import java.util.List;

public class ReviewServiceLogic {

    public void getReviews(int busId, getReviewsResponse r) {
        List<ReviewListCardModel> reviewModelsList = new ArrayList<>();
        String url = Const.GET_REVIEWS_BY_BUS_ID + String.valueOf(busId);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("REVIEWS", response.toString());
                for (int i = 0; i < response.length(); i++) {
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
        String url = "http://coms-309-020.class.las.iastate.edu:8080/review/" + String.valueOf(busId) + "/user/" + String.valueOf(userId) + "/createReview";

        Log.d("ADD REVIEW URL", url);

        HashMap<String, Object> params = new HashMap<>();
        params.put("reviewTxt", reviewTxt);
        params.put("reviewHeader", "Test");
        params.put("rateVal", ratingVal);

        Log.d("addReview - newReview", params.toString());


        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // After we do a POST for the new review, we need to update the business
                        // Total Review Count and Total Rating Sum to display / use for later calculations.
                        BusinessServiceLogic businessServiceLogic = new BusinessServiceLogic();
                        try {
                            businessServiceLogic.editRatingAndReviewCount(busId, ratingVal, 1, new businessStringResponse() {
                                @Override
                                public void onSuccess(String s) {
                                    r.onSuccess(response);
                                }

                                @SuppressLint("LongLogTag")
                                @Override
                                public void onError(String s) {
                                    Log.d("addReview - editRatingAndReviewCount - ERROR", s);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        ) {
            @Override
            public byte[] getBody() {
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        AppController.getInstance().addToRequestQueue(request);


    }
}
