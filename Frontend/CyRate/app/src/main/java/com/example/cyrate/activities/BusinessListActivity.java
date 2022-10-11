package com.example.cyrate.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyrate.Logic.BusinessServiceLogic;
import com.example.cyrate.Logic.getBusinessesResponse;
import com.example.cyrate.R;
import com.example.cyrate.models.BusinessListInterface;
import com.example.cyrate.adapters.BusinessListAdapter;
import com.example.cyrate.models.BusinessListCardModel;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class BusinessListActivity extends AppCompatActivity implements BusinessListInterface, NavigationView.OnNavigationItemSelectedListener {

    BusinessServiceLogic businessServiceLogic;
    BusinessListAdapter busListAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<BusinessListCardModel> businessListCardModel = new ArrayList<>();
    int[] restaurantImages = {R.drawable.provisions_hero};

    DrawerLayout drawerLayout;
    NavigationView navView;
    ImageView open_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        open_menu = (ImageView) findViewById(R.id.open_menu_icon);

        RecyclerView recyclerView = findViewById(R.id.restaurantList_recyclerView);
        layoutManager = new LinearLayoutManager(this);

        businessServiceLogic = new BusinessServiceLogic();
        busListAdapter = new BusinessListAdapter(this,
                businessListCardModel, this);

        Log.d("TEST 1", "BEFORE SET ADAPTER");
        recyclerView.setAdapter(busListAdapter);
        recyclerView.setLayoutManager(layoutManager);


        try {
            Log.d("TEST 1", "BEFORE SET BUS LIST CARD MODELS");
            setUpBusinessListCardModels(this, this, recyclerView);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        navigationDrawer();

        drawerLayout.setScrimColor(getResources().getColor(R.color.red));

    }


    // When menu is open and back button is pressed, we just close the menu instead of going
    // back a page
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void setUpBusinessListCardModels(Context ctx, BusinessListInterface busInterface,
                                             RecyclerView recyclerView) throws JSONException {


        businessServiceLogic.getBusinesses(new getBusinessesResponse() {
            @Override
            public void onSuccess(List<BusinessListCardModel> list) {
                for (int i = 0; i < list.size(); i++) {
                    businessListCardModel.add(list.get(i));
                }
//                busListAdapter = new BusinessListAdapter(ctx,
//                        businessListCardModel, busInterface);
                Log.d("TEST 1", "IN HERE");
                busListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onError(String s) {
                Log.d("TEST 1", s);
                Toast.makeText(BusinessListActivity.this, s.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, IndividualBusinessActivity.class);

        intent.putExtra("NAME", businessListCardModel.get(position).getBusName());
        intent.putExtra("CATEGORY", businessListCardModel.get(position).getBusType());
        intent.putExtra("ADDRESS", businessListCardModel.get(position).getLocation());
        intent.putExtra("RATING", "4.7"); // Hard coded for now
        intent.putExtra("HOURS", businessListCardModel.get(position).getHours());
        intent.putExtra("IMAGE", businessListCardModel.get(position).getPhotoUrl());
        intent.putExtra("PRICE_GAUGE", businessListCardModel.get(position).getPriceGauge());
        intent.putExtra("ID", businessListCardModel.get(position).getBusId());



        startActivity(intent);
    }

    private void navigationDrawer() {
        // Navigation Drawer
        navView.bringToFront();
        navView.setNavigationItemSelectedListener(this);
        navView.setCheckedItem(R.id.nav_restaurants);

        open_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_restaurants:
                break;
            case R.id.nav_addBusiness:
                Intent intent = new Intent(BusinessListActivity.this, AddBusinessActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                // code here
                break;
            case R.id.nav_edit_profile:
                Intent i = new Intent(BusinessListActivity.this, EditProfileActivity.class);
                startActivity(i);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}


