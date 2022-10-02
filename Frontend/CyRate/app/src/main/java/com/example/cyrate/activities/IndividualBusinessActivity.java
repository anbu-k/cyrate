package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DateTimePatternGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.R;


public class IndividualBusinessActivity extends AppCompatActivity {

    ImageView back_btn, busImage;
    TextView busName, rating, priceGauge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_business);

        Bundle extras = getIntent().getExtras();

        back_btn = (ImageView) findViewById(R.id.back_button_image);
        busImage = (ImageView) findViewById(R.id.restaurant_image);
        busName = (TextView) findViewById(R.id.restaurant_name);
        rating = (TextView) findViewById(R.id.ratings_text);
        priceGauge = (TextView) findViewById(R.id.price_text);

        new ImageLoaderTask(extras.getString("IMAGE"), busImage).execute();
        busName.setText(extras.getString("NAME"));
        rating.setText("Rating:" + extras.getString("RATING"));
        priceGauge.setText(extras.getString("PRICE_GAUGE"));


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateBack();
            }
        });
    }

    private void navigateBack(){
        Intent intent = new Intent(this, BusinessListActivity.class);
        startActivity(intent);
    }
}