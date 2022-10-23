package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyrate.Logic.ReviewInterfaces.getReviewsResponse;
import com.example.cyrate.Logic.ReviewServiceLogic;
import com.example.cyrate.R;
import com.example.cyrate.adapters.ReviewListAdapter;
import com.example.cyrate.models.RecyclerViewInterface;
import com.example.cyrate.models.ReviewListCardModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ReviewListActivity extends AppCompatActivity implements RecyclerViewInterface {

    ReviewServiceLogic reviewServiceLogic;
    ReviewListAdapter reviewListAdapter;
    Bundle extras;
    ArrayList<ReviewListCardModel> reviewListCardModels = new ArrayList<>();
    int[] profilePics = {R.drawable.profilepic};
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        extras = getIntent().getExtras();

        back_btn = (ImageView) findViewById(R.id.back_btn_icon);

        RecyclerView recyclerView = findViewById(R.id.reviewList_recyclerView);
        TextView emptyView = findViewById(R.id.empty_view);

        // Set this to non-visible initially
        emptyView.setVisibility(View.GONE);

        reviewServiceLogic = new ReviewServiceLogic();
        reviewListAdapter = new ReviewListAdapter(
                this, reviewListCardModels, this

        );
        recyclerView.setAdapter(reviewListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            setUpReviewModels(recyclerView, emptyView);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewListActivity.this, IndividualBusinessActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

   private void setUpReviewModels(RecyclerView recyclerView, TextView emptyView) throws JSONException {
        int busId = extras.getInt("ID");
        reviewServiceLogic.getReviews(busId, new getReviewsResponse() {
            @Override
            public void onSuccess(List<ReviewListCardModel> list) {
                for (int i = 0; i < list.size(); i++) {
                    reviewListCardModels.add(list.get(i));
                }
                Log.d("TEST 2", "IN HERE");
                reviewListAdapter.notifyDataSetChanged();

                if(list.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                else{
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String s) {
                Log.d("TEST 2", s);
                Toast.makeText(ReviewListActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
   }

    @Override
    // onClick for each card in the list
    public void onItemClick(int position) {
        Intent intent = new Intent(ReviewListActivity.this, IndividualReviewActivity.class);
        // Put in new extras for review info + prev extras (business info)
        intent.putExtras(extras);
        intent.putExtra("REVIEWER_NAME", reviewListCardModels.get(position).getReviewUser().getFullName());
        intent.putExtra("RATING_VAL", reviewListCardModels.get(position).getRateVal());
        intent.putExtra("REVIEW_BODY", reviewListCardModels.get(position).getReviewText());
        intent.putExtra("REVIEWER_PROFILE_PIC", reviewListCardModels.get(position).getReviewUser().getPhotoUrl());



        startActivity(intent);
    }
}