package com.example.cyrate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyrate.R;
import com.example.cyrate.models.UserModel;


import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button editProfileButton = findViewById(R.id.btn_editProfile);


        TextView userName = findViewById(R.id.user_name);
//        userName.setText(MainActivity.globalUser.getUserId());
        userName.setText("megan");

        final Handler handler = new Handler();


        Intent intent = new Intent(this, BusinessListActivity.class);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 5000);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });

    }
}
