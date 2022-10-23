package com.example.cyrate.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
import com.example.cyrate.Logic.BusinessInterfaces.getBusinessesResponse;
import com.example.cyrate.R;
import com.example.cyrate.models.RecyclerViewInterface;
import com.example.cyrate.adapters.BusinessListAdapter;
import com.example.cyrate.models.BusinessListCardModel;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class BusinessListActivity extends AppCompatActivity implements RecyclerViewInterface, NavigationView.OnNavigationItemSelectedListener {

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

        // Use this to hide any menu tabs depending on the user type
        hideMenuItems();

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
            setUpBusinessListCardModels();
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

    private void setUpBusinessListCardModels() throws JSONException {


        businessServiceLogic.getBusinesses(new getBusinessesResponse() {
            @Override
            public void onSuccess(List<BusinessListCardModel> list) {
                for (int i = 0; i < list.size(); i++) {
                    businessListCardModel.add(list.get(i));
                }
                Log.d("TEST 1", "IN HERE");
                busListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onError(String s) {
                Log.d("TEST 1", s);
                Toast.makeText(BusinessListActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    // onClick for each card in the list
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
        Intent i;
        switch(menuItem.getItemId()){
            case R.id.nav_restaurants:
                break;
            case R.id.nav_addBusiness:
                i = new Intent(BusinessListActivity.this, AddBusinessActivity.class);
                startActivity(i);
                break;
            case R.id.nav_profile:
                // code here
                break;
            case R.id.nav_edit_profile:
                i = new Intent(BusinessListActivity.this, EditProfileActivity.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
                i = new Intent(BusinessListActivity.this, LoginActivity.class);
                startActivity(i);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideMenuItems(){
        Menu navMenu = navView.getMenu();

        if (MainActivity.globalUser.getEmail().equals("guest-user-email")){
            // A guest user should not be able to edit the guest user profile
            navMenu.findItem(R.id.nav_edit_profile).setVisible(false);

            //guest cannot add business
            navMenu.findItem(R.id.nav_addBusiness).setVisible(false);

            //guest cannot log out
            navMenu.findItem(R.id.nav_logout).setVisible(false);

            //guest cannot see their profile
            navMenu.findItem(R.id.nav_profile).setVisible(false);

            //guest CAN sign in

        }
    }

}


