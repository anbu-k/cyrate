package com.example.cyrate.activities;

import static com.example.cyrate.activities.MainActivity.globalUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.Logic.BusinessServiceLogic;
import com.example.cyrate.NavMenuUtils;
import com.example.cyrate.R;
import com.example.cyrate.adapters.BusinessListAdapter;
import com.example.cyrate.models.BusinessListCardModel;
import com.example.cyrate.models.RecyclerViewInterface;
import com.google.android.material.navigation.NavigationView;
import com.example.cyrate.Logic.BusinessInterfaces.getBusinessesResponse;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class WelcomeToCyRateActivity extends AppCompatActivity implements RecyclerViewInterface, NavigationView.OnNavigationItemSelectedListener {
    TextView name, username, email, phone;
    Button editProfileButton;
    ImageView profilePic;

    DrawerLayout drawerLayout;
    NavigationView navView;
    ImageView open_menu;

//    LinearLayoutManager layoutManager;
    BusinessServiceLogic businessServiceLogic;

    RecyclerView favoritesRecycler;

    BusinessListAdapter busListAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<BusinessListCardModel> businessListCardModel = new ArrayList<>();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_to_cyrate);

        Log.d("welcome", "looking for profile elements");

        // Profile elements
        name = findViewById(R.id.name_text);
        Log.d("welcome", "found name");
        username = findViewById(R.id.username_text);
        Log.d("welcome", "found username");
        email = findViewById(R.id.email_text);
        Log.d("welcome", "found email");
        phone = findViewById(R.id.phone_text);
        Log.d("welcome", "found phone");
        editProfileButton = findViewById(R.id.btn_edit_profile);
        Log.d("welcome", "found button");
        profilePic = findViewById(R.id.profilePicPic);
        Log.d("welcome", "found profile pic");

        Log.d("welcome", "setting profile elements");

        name.setText(globalUser.getFullName());
        username.setText(globalUser.getUsername());
        email.setText(globalUser.getEmail());
        new ImageLoaderTask(globalUser.getPhotoUrl(), profilePic).execute();

        //menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        open_menu = findViewById(R.id.open_menu_icon);

        navigationDrawer();

        drawerLayout.setScrimColor(getResources().getColor(R.color.red));

        // Use this to hide any menu tabs depending on the user type
        NavMenuUtils.hideMenuItems(navView.getMenu());


        //favorites
        favoritesRecycler = findViewById(R.id.favorites_recycler);
        layoutManager = new LinearLayoutManager(this);


//        layoutManager = new LinearLayoutManager(this);
        businessServiceLogic = new BusinessServiceLogic();
        busListAdapter = new BusinessListAdapter(this,
                businessListCardModel, this);

        favoritesRecycler.setAdapter(busListAdapter);
        favoritesRecycler.setLayoutManager(layoutManager);

        try {
            setFavorites();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("welcome", "setting lsitener");
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeToCyRateActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });
    }

    private void setFavorites() throws JSONException {
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
                Log.d("set favorites", s);
                Toast.makeText(WelcomeToCyRateActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void navigationDrawer() {
        // Navigation Drawer
        navView.bringToFront();
        navView.setNavigationItemSelectedListener(this);
        navView.setCheckedItem(R.id.nav_home);

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
        if (menuItem.getItemId() != R.id.nav_home){
            NavMenuUtils.onNavItemSelected(menuItem, WelcomeToCyRateActivity.this);
        }
        else{
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, IndividualBusinessActivity.class);

        intent.putExtra("NAME", businessListCardModel.get(position).getBusName());
        intent.putExtra("CATEGORY", businessListCardModel.get(position).getBusType());
        intent.putExtra("ADDRESS", businessListCardModel.get(position).getLocation());
        intent.putExtra("HOURS", businessListCardModel.get(position).getHours());
        intent.putExtra("IMAGE", businessListCardModel.get(position).getPhotoUrl());
        intent.putExtra("PRICE_GAUGE", businessListCardModel.get(position).getPriceGauge());
        intent.putExtra("ID", businessListCardModel.get(position).getBusId());
        intent.putExtra("RATING_SUM", businessListCardModel.get(position).getReviewSum());
        intent.putExtra("REVIEW_COUNT", businessListCardModel.get(position).getReviewCount());
        intent.putExtra("PREVIOUS_ACTIVITY", "WelcomeToCyrateActivity");



        startActivity(intent);
    }
}
