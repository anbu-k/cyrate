package com.example.cyrate.Logic;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.models.BusinessListCardModel;
import com.example.cyrate.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BusinessServiceLogic {


    /**
     * Makes a request to the server to fetch all the
     * businesses and returns a List of the BusinessListCardModels
     *
     * @param r - Interface used for async callbacks to the BusinessListActivity
     */
    public void getBusinesses(getBusinessesResponse r) throws JSONException {
        List<BusinessListCardModel> businessModelsList = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                Const.GET_BUSINESSES_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        // Get each business from the JSON array
                        JSONObject business = (JSONObject) response.get(i);
                        Log.d("JSON OBJ", business.toString());

                        // Some priceGauges are null, lets do a check first
                        String priceGauge = business.get("priceGauge").toString().equals("null") ? "$" : business.get("priceGauge").toString();

                        BusinessListCardModel businessListCardModel = new BusinessListCardModel(
                                (int) business.get("busId"),
                                business.get("busName").toString(),
                                business.get("busType").toString(),
                                business.get("photoUrl").toString(),
                                business.get("hours").toString(),
                                business.get("location").toString(),
                                (int) business.get("ownerId"),
                                business.get("menuLink").toString(),
                                priceGauge,
                                (int) business.get("reviewSum"),
                                (int) business.get("reviewCount")
                        );
                        businessModelsList.add(businessListCardModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // Send the businessModelsList back to the BusinessListActivity
                // as a async callback
                r.onSuccess(businessModelsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                return;
            }
        }

        );

        AppController.getInstance().addToRequestQueue(request);
    }

}

