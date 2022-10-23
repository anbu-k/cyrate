package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.R;

public class IndividualReviewActivity extends AppCompatActivity {

    ImageView back_btn, reviewerProfilePic;
    TextView reviewerName, reviewBody;
    RatingBar ratingBar;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_review);

        back_btn = (ImageView) findViewById(R.id.back_button_image);
        reviewerProfilePic = findViewById(R.id.profilePic);
        reviewerName = findViewById(R.id.reviewerNameIndiv);
        reviewBody = findViewById(R.id.reviewBody);
        ratingBar = findViewById(R.id.reviewRating);

        extras = getIntent().getExtras();
        String fullName = extras.getString("REVIEWER_NAME");
        String displayName = fullName.isEmpty() ? "Anonymous User" : fullName;

        new ImageLoaderTask(extras.getString("REVIEWER_PROFILE_PIC"), reviewerProfilePic).execute();
        reviewerName.setText(displayName);
        reviewBody.setText(extras.getString("REVIEW_BODY"));
        ratingBar.setRating(extras.getInt("RATING_VAL"));

        // Navigates back to review list
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndividualReviewActivity.this, ReviewListActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}