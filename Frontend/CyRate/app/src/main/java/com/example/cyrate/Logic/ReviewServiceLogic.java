package com.example.cyrate.Logic;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.Logic.ReviewInterfaces.getReviewsResponse;
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
}
