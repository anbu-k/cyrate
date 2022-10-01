package com.example.cyrate.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyrate.R;
import com.example.cyrate.models.BusinessListInterface;
import com.example.cyrate.adapters.BusinessListAdapter;
import com.example.cyrate.models.BusinessListCardModel;

import java.util.ArrayList;

public class BusinessListActivity extends AppCompatActivity implements BusinessListInterface {

    ArrayList<BusinessListCardModel> businessListCardModel = new ArrayList<>();
    int[] restaurantImages = {R.drawable.provisions_hero};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        RecyclerView recyclerView = findViewById(R.id.restaurantList_recyclerView);

        setUpBusinessListCardModels();

        BusinessListAdapter busListAdapter = new BusinessListAdapter(this,
                businessListCardModel, this);

        recyclerView.setAdapter(busListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpBusinessListCardModels() {
        String[] restaurantNames = getResources().getStringArray(R.array.restaurantListNames);
        String[] restaurantCategories = getResources().getStringArray(R.array.restaurantListCategory);
        String[] restaurantAddresses = getResources().getStringArray(R.array.restaurantListAddress);
        String[] restaurantRatings = getResources().getStringArray(R.array.restaurantListRating);
        String[] restaurantHours = getResources().getStringArray(R.array.restaurantListHours);



        // Hard coded 15 for now to get 15 cards, this will be dynamic later when we get
        // real restaurant data
//        for (int i = 0; i < 15; i++) {
//            businessListCardModel.add(new RestaurantListCardModel(
//                    restaurantNames[0],
//                    restaurantCategories[0],
//                    restaurantAddresses[0],
//                    restaurantRatings[0],
//                    restaurantHours[0],
//                    restaurantImages[0]
//            ));
//        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, IndividualBusinessActivity.class);

//        intent.putExtra("NAME", businessListCardModel.get(position).getName());
//        intent.putExtra("CATEGORY", businessListCardModel.get(position).getCategory());
//        intent.putExtra("ADDRESS", businessListCardModel.get(position).getAddress());
//        intent.putExtra("RATING", businessListCardModel.get(position).getRating());
//        intent.putExtra("HOURS", businessListCardModel.get(position).getHours());
//        intent.putExtra("IMAGE", businessListCardModel.get(position).getImg());

        startActivity(intent);
    }
}
