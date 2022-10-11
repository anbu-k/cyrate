package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cyrate.Logic.BusinessServiceLogic;
import com.example.cyrate.R;
import com.example.cyrate.adapters.BusinessListAdapter;
import com.example.cyrate.adapters.ReviewListAdapter;
import com.example.cyrate.models.BusinessListCardModel;
import com.example.cyrate.models.ReviewListCardModel;

import java.util.ArrayList;

public class ReviewListActivity extends AppCompatActivity {

    ArrayList<ReviewListCardModel> reviewListCardModels = new ArrayList<>();
    int[] profilePics = {R.drawable.profilepic};
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        Bundle extras = getIntent().getExtras();

        back_btn = (ImageView) findViewById(R.id.back_btn_icon);

        RecyclerView recyclerView = findViewById(R.id.reviewList_recyclerView);
        setUpReviewModels();

        ReviewListAdapter reviewListAdapter = new ReviewListAdapter(
                this, reviewListCardModels
        );
        recyclerView.setAdapter(reviewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewListActivity.this, IndividualBusinessActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

    private void setUpReviewModels() {
        for (int i = 0; i < 20; i++) {
            reviewListCardModels.add(new ReviewListCardModel(
                    "John Doe",
                    "I'm SO glad I made the detour off I-35 to enjoy this place, as well as the fabulous Main Street.",
                    profilePics[0]
            ));
        }
    }
}