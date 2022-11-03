package com.example.cyrate.Logic;

import com.example.cyrate.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

public class FavoritesServiceLogic {

    public void addFavorite(int userId, int businessId) throws JSONException {
        String url = Const.ADD_FAVORITE_URL + String.valueOf(businessId) + "/user/" + String.valueOf(userId);

//        JSONObject newFavoriteObject = new

    }
}
