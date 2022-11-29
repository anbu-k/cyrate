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
import com.example.cyrate.models.CommentThreadCardModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentThreadAdapter extends RecyclerView.Adapter<CommentThreadAdapter.MyViewHolder>{

    Context ctx;
    ArrayList<CommentThreadCardModel> commentListCardModels;


    public CommentThreadAdapter(
            Context ctx,
            ArrayList<CommentThreadCardModel> commentListCardModels
    ){
        this.ctx = ctx;
        this.commentListCardModels = commentListCardModels;
    }

    @NonNull
    @Override
    public CommentThreadAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.comment_thread_card, parent, false);
        return new CommentThreadAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentThreadAdapter.MyViewHolder holder, int position) {
        new ImageLoaderTask(commentListCardModels.get(position).getPhotoUrl(), holder.profilePic).execute();


        holder.commenterName.setText(commentListCardModels.get(position).getName());
        holder.commentText.setText(commentListCardModels.get(position).getCommentBody());
        holder.date.setText(String.valueOf(commentListCardModels.get(position).getDate()));

        // If this is the last comment in the thread, remove the gray thread bar
        if(position == commentListCardModels.size() - 1){
            holder.threadBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return commentListCardModels.size();
    }

    // Class necessary and is similar for having an onCreate method. Allows us to get all our views
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePic;
        TextView commenterName, commentText, date;
        View threadBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.commentThread_profilePic);
            commenterName = itemView.findViewById(R.id.commentThread_name);
            commentText = itemView.findViewById(R.id.commentThread_bodyText);
            date = itemView.findViewById(R.id.commentThread_date);
            threadBar = itemView.findViewById(R.id.threadBar);


        }
    }

    public void addItem(JSONObject obj){
        try {
            CommentThreadCardModel commentModel = new CommentThreadCardModel(
                    obj.getString("commenterName"),
                    obj.getString("photoUrl"),
                    obj.getString("commentBody"),
                    obj.getString("date")
            );

            commentListCardModels.add(commentModel);
            notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
