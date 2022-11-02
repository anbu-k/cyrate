package com.example.cyrate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.R;
import com.example.cyrate.models.BusinessPostCardModel;
import com.example.cyrate.models.RecyclerViewInterface;
import com.example.cyrate.models.ReviewListCardModel;

import java.util.ArrayList;

public class BusinessFeedAdapter extends RecyclerView.Adapter<BusinessFeedAdapter.MyViewHolder> {

    Context ctx;
    ArrayList<BusinessPostCardModel> businessPostList;

    public BusinessFeedAdapter(
            Context ctx,
            ArrayList<BusinessPostCardModel> businessPostList
    ){
        this.ctx = ctx;
        this.businessPostList = businessPostList;
    }

    @NonNull
    @Override
    public BusinessFeedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout. business_post_card, parent, false);
        return new BusinessFeedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessFeedAdapter.MyViewHolder holder, int position) {
        new ImageLoaderTask(businessPostList.get(position).getBusiness().getPhotoUrl(), holder.busProfilePic).execute();
        new ImageLoaderTask(businessPostList.get(position).getPhotoUrl(), holder.busPostPhoto).execute();

        holder.busName.setText(businessPostList.get(position).getBusiness().getBusName());
        holder.busPostDate.setText(businessPostList.get(position).getDate());
        holder.busPostText.setText(businessPostList.get(position).getPostTxt());
    }

    @Override
    public int getItemCount() {
        return businessPostList.size();
    }

    // Class necessary and is similar for having an onCreate method. Allows us to get all our views
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView busProfilePic, busPostPhoto;
        TextView busName, busPostDate, busPostText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            busProfilePic = itemView.findViewById(R.id.busProfilePic);
            busPostPhoto = itemView.findViewById(R.id.busPost_photo);
            busName = itemView.findViewById(R.id.busPost_name);
            busPostDate = itemView.findViewById(R.id.busPost_date);
            busPostText = itemView.findViewById(R.id.busPost_bodyText);

        }
    }

}
