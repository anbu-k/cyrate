package com.example.cyrate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.cyrate.R;


import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView userName = findViewById(R.id.user_name);
        userName.setText(MainActivity.globalUser.getFullName());
//        userName.setText("megan");

        final Handler handler = new Handler();


        Intent intent = new Intent(this, BusinessListActivity.class);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 5000);

    }
}
