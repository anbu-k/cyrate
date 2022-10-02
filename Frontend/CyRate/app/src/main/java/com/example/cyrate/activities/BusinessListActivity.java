package com.example.cyrate.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyrate.Logic.BusinessServiceLogic;
import com.example.cyrate.Logic.getBusinessesResponse;
import com.example.cyrate.R;
import com.example.cyrate.models.BusinessListInterface;
import com.example.cyrate.adapters.BusinessListAdapter;
import com.example.cyrate.models.BusinessListCardModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class BusinessListActivity extends AppCompatActivity implements BusinessListInterface {

    BusinessServiceLogic businessServiceLogic;
    ArrayList<BusinessListCardModel> businessListCardModel = new ArrayList<>();
    int[] restaurantImages = {R.drawable.provisions_hero};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        RecyclerView recyclerView = findViewById(R.id.restaurantList_recyclerView);

        businessServiceLogic = new BusinessServiceLogic();

        try {
            setUpBusinessListCardModels(this, this, recyclerView);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void setUpBusinessListCardModels(Context ctx, BusinessListInterface busInterface,
                                             RecyclerView recyclerView) throws JSONException {

        businessServiceLogic.getBusinesses(new getBusinessesResponse() {
            @Override
            public void onSuccess(List<BusinessListCardModel> list) {
                for(int i = 0; i < list.size(); i++){
                    businessListCardModel.add(list.get(i));
                }
                BusinessListAdapter busListAdapter = new BusinessListAdapter(ctx,
                        businessListCardModel, busInterface);

                recyclerView.setAdapter(busListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            }

            @Override
            public void onError(String s) {

            }
        });
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


