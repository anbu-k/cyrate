package com.example.cyrate.activities;

import static com.example.cyrate.activities.MainActivity.globalUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.R;

public class WelcomeToCyRateActivity extends AppCompatActivity {
    TextView username;
    Button continueButton;
    ImageView profilePic;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_to_cyrate);

        username = findViewById(R.id.username_text);
        continueButton = findViewById(R.id.btn_continue);
        profilePic = findViewById(R.id.profile_pic);

        username.setText(globalUser.getUsername());
        new ImageLoaderTask(globalUser.getPhotoUrl(), profilePic).execute();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeToCyRateActivity.this, BusinessListActivity.class);
                startActivity(i);
            }
        });
    }
}
