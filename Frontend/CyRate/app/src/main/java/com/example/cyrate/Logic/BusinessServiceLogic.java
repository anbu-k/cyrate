package com.example.cyrate.Logic;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.R;
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
        Log.d("TEST 1", "IN GETBUSSINESS");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                Const.GET_BUSINESSES_URL, null, response -> {
            Log.d("TEST 1", "IN ON RESPONSE");

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
        }, error -> r.onError(error.toString())

        );

        AppController.getInstance().addToRequestQueue(request);
    }

    /**
     * Adds a business to the DB
     *
     * @param busName
     * @param busType
     * @param busHours
     * @param busLocation
     * @param priceGauge
     * @param photoUrl
     * @param r
     * @throws JSONException
     */
    public void addBusiness(String busName, String busType, String busHours, String busLocation,
                            String priceGauge, String photoUrl, addBusinessResponse r) throws JSONException {
        String url = Const.ADD_BUSINESS_URL;

        JSONObject newUserObj = new JSONObject();
        newUserObj.put("busName", busName);
        newUserObj.put("busType", busType);
        newUserObj.put("hours", busHours);
        newUserObj.put("location", busLocation);
        newUserObj.put("priceGauge", priceGauge);
        newUserObj.put("photoUrl", photoUrl);

        // Defaults to fill the required JSON object
        newUserObj.put("ownerId", -1);
        newUserObj.put("menuLink", "");
        newUserObj.put("reviewSum", 0);
        newUserObj.put("reviewCount", 0);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, newUserObj,
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
                        r.onError(error.getMessage());
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(request);


    }

    public void deleteBusiness(deleteBusinessResponse r, int businessId) throws JSONException {
        String url = Const.DELETE_BUSINESS_URL + String.valueOf(businessId);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                r.onSuccess("Business Deleted");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                r.onError(error.toString());
            }
        }

        );

        AppController.getInstance().addToRequestQueue(request);
    }

}

