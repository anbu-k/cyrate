package com.example.cyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroActivity extends AppCompatActivity {

    ImageView background, logo;
    TextView welcomeTxt, cyrateTxt;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        background = findViewById(R.id.bg_image);
        logo = findViewById(R.id.cy_logo);
        welcomeTxt = findViewById(R.id.txt_welcome);
        cyrateTxt = findViewById(R.id.txt_cyrate);
        lottieAnimationView = findViewById(R.id.cycloneAnimation);

        background.animate().translationY(-4000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
        welcomeTxt.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
        cyrateTxt.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(3000).setDuration(1000).setStartDelay(4000);



    }
}