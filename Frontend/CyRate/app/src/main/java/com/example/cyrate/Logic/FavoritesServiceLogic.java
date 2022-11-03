package com.example.cyrate.Logic;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cyrate.AppController;
import com.example.cyrate.Logic.BusinessInterfaces.getBusinessesResponse;
import com.example.cyrate.activities.IntroActivity;
import com.example.cyrate.models.BusinessListCardModel;
import com.example.cyrate.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavoritesServiceLogic {

    public void addFavorite(int userId, int businessId) throws JSONException {
        String url = Const.ADD_FAVORITE_URL + String.valueOf(businessId) + "/user/" + String.valueOf(userId);

//        JSONObject newFavoriteObject = new

    }

    public void getFavoritesByUser(int uid, getBusinessesResponse r){
        String url = Const.GET_FAVORITES_BY_USER_URL + String.valueOf(uid);
        ArrayList<BusinessListCardModel> favoriteBusinesses = new ArrayList<>();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++){
                try{
                    Log.d("favs", "in try");
                    //get each business
                    BusinessListCardModel newFavorite;
                    JSONObject row = (JSONObject) response.get(i);
                    JSONObject business = row.getJSONObject("business");
                    Log.d("favs", business.toString());
                    Log.d("favs", "busId: " + business.getInt("busId") + "   /   " + business.getString("busId"));

                    int busId = business.getInt("busId");
                    String busName = business.getString("busName");
                    String busType = business.getString("busType");
                    String photoUrl = business.getString("photoUrl");
                    String hours = business.getString("hours");
                    String location = business.getString("location");
                    int ownerId = business.getInt("ownerId");
                    String menuLink = business.getString("menuLink");
                    String priceGauge = business.getString("priceGauge");
                    int reviewSum = business.getInt("reviewSum");
                    int reviewCount = business.getInt("reviewCount");

                    newFavorite = new BusinessListCardModel(busId, busName, busType, photoUrl, hours, location, ownerId, menuLink, priceGauge, reviewSum, reviewCount);

                    Log.d("favs", busName);
                    favoriteBusinesses.add(newFavorite);
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }r.onSuccess(favoriteBusinesses);
        }, error -> r.onError(error.toString())
        );

        AppController.getInstance().addToRequestQueue(arrayRequest);
    }
}