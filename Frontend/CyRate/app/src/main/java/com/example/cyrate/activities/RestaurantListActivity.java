package com.example.cyrate.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyrate.R;
import com.example.cyrate.RestaurantListInterface;
import com.example.cyrate.adapters.RestaurantListAdapter;
import com.example.cyrate.models.RestaurantListCardModel;

import java.util.ArrayList;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantListInterface {

    ArrayList<RestaurantListCardModel> restaurantCardListModels = new ArrayList<>();
    int[] restaurantImages = {R.drawable.provisions_hero};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        RecyclerView recyclerView = findViewById(R.id.restaurantList_recyclerView);

        setUpRestaurantCardListModels();

        RestaurantListAdapter restListAdapter = new RestaurantListAdapter(this,
                restaurantCardListModels, this);

        recyclerView.setAdapter(restListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, IndividualRestaurantActivity.class);

        intent.putExtra("NAME", restaurantCardListModels.get(position).getName());
        intent.putExtra("CATEGORY", restaurantCardListModels.get(position).getCategory());
        intent.putExtra("ADDRESS", restaurantCardListModels.get(position).getAddress());
        intent.putExtra("RATING", restaurantCardListModels.get(position).getRating());
        intent.putExtra("HOURS", restaurantCardListModels.get(position).getHours());
        intent.putExtra("IMAGE", restaurantCardListModels.get(position).getImg());

        startActivity(intent);
    }
}
