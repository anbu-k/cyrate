package com.example.cyrate.activities;

import static com.example.cyrate.activities.MainActivity.globalUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.NavMenuUtils;
import com.example.cyrate.R;
import com.google.android.material.navigation.NavigationView;

public class WelcomeToCyRateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView name, username, email, phone;
    Button editProfileButton;
    ImageView profilePic;

    DrawerLayout drawerLayout;
    NavigationView navView;
    ImageView open_menu;


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

        Log.d("welcome", "setting lsitener");
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeToCyRateActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });
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
        if (menuItem.getItemId() != R.id.nav_restaurants){
            NavMenuUtils.onNavItemSelected(menuItem, WelcomeToCyRateActivity.this);
        }
        else{
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
