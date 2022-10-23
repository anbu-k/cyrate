package com.example.cyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cyrate.activities.ReviewListActivity;

public class AddReviewActivity extends AppCompatActivity {

    ImageView backBtn, submitBtn, busPhoto;
    TextView busName;
    RatingBar ratingBar;
    EditText reviewBody;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        backBtn = findViewById(R.id.back_btn_addReview);
        submitBtn = findViewById(R.id.submitReview_icon);
        busPhoto = findViewById(R.id.busImage_addReview);
        busName = findViewById(R.id.busName_addReview);
        ratingBar = findViewById(R.id.ratingBar_addReview);
        reviewBody = findViewById(R.id.reviewBody_addReview);

        extras = getIntent().getExtras();

        new ImageLoaderTask(extras.getString("IMAGE"), busPhoto).execute();
        busName.setText(extras.getString("NAME"));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddReviewActivity.this, ReviewListActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });



    }
}