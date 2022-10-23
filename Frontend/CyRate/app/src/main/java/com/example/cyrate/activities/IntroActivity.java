package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cyrate.R;
import com.example.cyrate.models.UserModel;

public class IntroActivity extends AppCompatActivity {

    ImageView background, logo;
    TextView welcomeTxt, cyrateTxt;
    LottieAnimationView lottieAnimationView;

    private static final int NUM_PAGES = 2;
//    private ViewPager viewPager;
//    private ScreenSlidePageAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        final Handler handler = new Handler();

        //set some default user stuff
        MainActivity.globalUser = new UserModel("anbu@gmail.com", "1234567");
        MainActivity.globalUser.setUsername("anbuk");
        MainActivity.globalUser.setPhotoUrl("https://sumaleeboxinggym.com/wp-content/uploads/2018/06/Generic-Profile-1600x1600.png");

        background = findViewById(R.id.bg_image);
        logo = findViewById(R.id.cy_logo);
        welcomeTxt = findViewById(R.id.txt_welcome);
        cyrateTxt = findViewById(R.id.txt_cyrate);
        lottieAnimationView = findViewById(R.id.cycloneAnimation);

//        viewPager = findViewById(R.id.viewPager);
//        pagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);

        background.animate().translationY(-4000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
        welcomeTxt.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
        cyrateTxt.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(3000).setDuration(1000).setStartDelay(4000);


        Intent intent = new Intent(this, LoginActivity.class);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 5000);

    }

//    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {
//
//        public ScreenSlidePageAdapter(@NonNull FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return new LoginTabFragment();
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }
}

