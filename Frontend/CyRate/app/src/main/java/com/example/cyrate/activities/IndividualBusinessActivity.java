package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.Logic.BusinessServiceLogic;
import com.example.cyrate.Logic.BusinessInterfaces.businessStringResponse;
import com.example.cyrate.R;
import com.example.cyrate.UserType;

import org.json.JSONException;


public class IndividualBusinessActivity extends AppCompatActivity {
    // test push
    Button findUs_btn, reviews_btn;
    ImageView back_btn, busImage, delete_btn, edit_btn;
    TextView busName, rating, priceGauge, reviewCount;
    String busNameString;
    int busId;
    BusinessServiceLogic businessServiceLogic;

    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_business);

        extras = getIntent().getExtras();
        busNameString = extras.getString("NAME");

        back_btn = (ImageView) findViewById(R.id.back_button_image);
        delete_btn = (ImageView) findViewById(R.id.delete_icon);
        edit_btn = (ImageView) findViewById(R.id.edit_icon);
        findUs_btn = (Button) findViewById(R.id.find_us_btn);
        reviews_btn = (Button) findViewById(R.id.reviews_btn);
        reviewCount = findViewById(R.id.reviews_text);



        busImage = (ImageView) findViewById(R.id.restaurant_image);
        busName = (TextView) findViewById(R.id.restaurant_name);
        rating = (TextView) findViewById(R.id.ratings_text);
        priceGauge = (TextView) findViewById(R.id.price_text);

        int totalReviews = extras.getInt("REVIEW_COUNT");
        int ratingSum = extras.getInt("RATING_SUM");

        float avgRating = totalReviews == 0 ? 0 : ratingSum / (float) totalReviews;
        rating.setText(String.format("%.1f", avgRating));
        reviewCount.setText(String.valueOf(totalReviews));

        new ImageLoaderTask(extras.getString("IMAGE"), busImage).execute();
        busName.setText(busNameString);
        priceGauge.setText(extras.getString("PRICE_GAUGE"));
        busId = extras.getInt("ID");

        businessServiceLogic = new BusinessServiceLogic();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateBack();
            }
        });

        findUs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndividualBusinessActivity.this, FindUsActivity.class);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        reviews_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndividualBusinessActivity.this, ReviewListActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndividualBusinessActivity.this, EditBusinessActivity.class);

                // Pass our extras from Business List to here (Individual Business Act) to EditBusiness Act
                intent.putExtras(extras);
                startActivity(intent);
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
                                try {
                                    businessServiceLogic.deleteBusiness(
                                            new businessStringResponse() {
                                                @Override
                                                public void onSuccess(String s) {
                                                    Toast.makeText(IndividualBusinessActivity.this,
                                                            "Successfully Deleted " + busNameString, Toast.LENGTH_LONG).show();

                                                    final Handler handler = new Handler();


                                                    Intent intent = new Intent(IndividualBusinessActivity.this, BusinessListActivity.class);

                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            startActivity(intent);
                                                        }
                                                    }, 3000);
                                                }

                                                @Override
                                                public void onError(String s) {
                                                    Log.d("DELETE BUSSINESS ERROR", s);
                                                    Toast.makeText(IndividualBusinessActivity.this, s, Toast.LENGTH_LONG).show();
                                                }
                                            }, busId
                                    );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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

        hideButtons();

    }

    private void navigateBack() {
        Intent intent;
        String prevActivity = extras.getString("PREVIOUS_ACTIVITY");
        if (prevActivity.equals("WelcomeToCyrateActivity")){
            intent = new Intent(this, WelcomeToCyRateActivity.class);
        }
        else {
            intent = new Intent(this, BusinessListActivity.class);
        }
        startActivity(intent);
    }

    private void hideButtons(){
        if (MainActivity.globalUser.getUserType() == UserType.GUEST){
            edit_btn.setVisibility(View.GONE);
            delete_btn.setVisibility(View.GONE);
        }
        if (MainActivity.globalUser.getUserType() == UserType.BUSINESS_OWNER){
            delete_btn.setVisibility(View.GONE);
        }
        else if(MainActivity.globalUser.getUserType() == UserType.BASIC_USER){
            edit_btn.setVisibility(View.GONE);
            delete_btn.setVisibility(View.GONE);
        }
    }
}