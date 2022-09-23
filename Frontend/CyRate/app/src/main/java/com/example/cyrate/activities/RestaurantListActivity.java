package com.example.cyrate.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cyrate.R;
import com.example.cyrate.models.RestaurantListCardModel;

import java.util.ArrayList;

public class RestaurantListActivity extends AppCompatActivity {

    ArrayList<RestaurantListCardModel> restaurantCardListModels = new ArrayList<>();
    int[] restaurantImages = {R.drawable.provisions_hero};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        setUpRestaurantCardListModels();
    }

    private void setUpRestaurantCardListModels() {
        String[] restaurantNames = getResources().getStringArray(R.array.restaurantListNames);
        String[] restaurantCategories = getResources().getStringArray(R.array.restaurantListCategory);
        String[] restaurantAddresses = getResources().getStringArray(R.array.restaurantListAddress);
        String[] restaurantRatings = getResources().getStringArray(R.array.restaurantListRating);
        String[] restaurantHours = getResources().getStringArray(R.array.restaurantListHours);

        // Hard coded 15 for now to get 15 cards, this will be dynamic later when we get
        // real restaurant data
        for (int i = 0; i < 15; i++) {
            restaurantCardListModels.add(new RestaurantListCardModel(
                    restaurantNames[0],
                    restaurantCategories[0],
                    restaurantAddresses[0],
                    restaurantRatings[0],
                    restaurantHours[0],
                    restaurantImages[0]
            ));
        }
    }
}
