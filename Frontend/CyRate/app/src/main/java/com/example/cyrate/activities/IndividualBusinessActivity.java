package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DateTimePatternGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.R;


public class IndividualBusinessActivity extends AppCompatActivity {

    ImageView back_btn, busImage, delete_btn;
    TextView busName, rating, priceGauge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_business);

        Bundle extras = getIntent().getExtras();

        back_btn = (ImageView) findViewById(R.id.back_button_image);
        delete_btn = (ImageView) findViewById(R.id.delete_icon);
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

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IndividualBusinessActivity.this);

                builder.setMessage("Are you sure you want to delete this business?")
                        .setCancelable(false)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(IndividualBusinessActivity.this, "Works", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    private void navigateBack() {
        Intent intent = new Intent(this, BusinessListActivity.class);
        startActivity(intent);
    }
}