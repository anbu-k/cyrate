package com.example.cyrate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.cyrate.R;


import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

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
