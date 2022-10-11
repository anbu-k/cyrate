package com.example.cyrate.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyrate.R;
import com.example.cyrate.models.BusinessListCardModel;
import com.example.cyrate.models.BusinessListInterface;
import com.example.cyrate.models.ReviewListCardModel;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder> {

    Context ctx;
    ArrayList<ReviewListCardModel> reviewListCardModels;

    public ReviewListAdapter(
            Context ctx,
            ArrayList<ReviewListCardModel> reviewListCardModels
    ){
        this.ctx = ctx;
        this.reviewListCardModels = reviewListCardModels;
    }

    @NonNull
    @Override
    public ReviewListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.review_list_card, parent, false);
        return new ReviewListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListAdapter.MyViewHolder holder, int position) {
        holder.profilePic.setImageResource(reviewListCardModels.get(position).getImage());
        holder.reviewerName.setText(reviewListCardModels.get(position).getReviewerName());
        holder.review.setText(reviewListCardModels.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return reviewListCardModels.size();
    }

    // Class necessary and is similar for having an onCreate method. Allows us to get all our views
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePic;
        TextView reviewerName, review;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.profilePic);
            reviewerName = itemView.findViewById(R.id.reviewerName);
            review = itemView.findViewById(R.id.review_txt);
        }
    }
}
