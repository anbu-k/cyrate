package com.example.cyrate.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cyrate.R;
import com.example.cyrate.RestaurantListInterface;
import com.example.cyrate.models.RestaurantListCardModel;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {
    private final RestaurantListInterface restaurantListInterface;
    Context ctx;
    ArrayList<RestaurantListCardModel> restaurantCardList;

    public RestaurantListAdapter(
            Context ctx,
            ArrayList<RestaurantListCardModel> restaurantCardList,
            RestaurantListInterface restaurantListInterface
            ){
        this.ctx = ctx;
        this.restaurantCardList = restaurantCardList;
        this.restaurantListInterface = restaurantListInterface;
    }
    // This is where we inflate our layout (RestaurantListCard) for each of our rows in the view
    @NonNull
    @Override
    public RestaurantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.restauarnt_list_card, parent, false);
        return new RestaurantListAdapter.MyViewHolder(view, restaurantListInterface);
    }

    // Since this is a recycle view, cards will be discarded when they go off screen.
    // However, when a card goes off screen, another will come on screen.
    // The new card will be 'binded' basically just updating the data for our
    // RestaurantListCardModel (name, address, etc..)
    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.MyViewHolder holder, int position) {
        holder.restName.setText(restaurantCardList.get(position).getName());
        holder.restAddress.setText(restaurantCardList.get(position).getAddress());
        holder.restCategory.setText(restaurantCardList.get(position).getCategory());
        holder.restRating.setText(restaurantCardList.get(position).getRating());
        holder.restHours.setText(restaurantCardList.get(position).getHours());
        holder.restImg.setImageResource(restaurantCardList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return restaurantCardList.size();
    }

    // Class necessary and is similar for having an onCreate method. Allows us to get all our views
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView restImg;
        TextView restName, restCategory, restAddress, restRating, restHours;

        public MyViewHolder(@NonNull View itemView, RestaurantListInterface restaurantListInterface) {
            super(itemView);

            restImg = itemView.findViewById(R.id.restaurant_img);
            restName = itemView.findViewById(R.id.restaurant_name);
            restCategory = itemView.findViewById(R.id.restaurant_category);
            restAddress = itemView.findViewById(R.id.restaurant_address);
            restRating = itemView.findViewById(R.id.restaurant_rating);
            restHours = itemView.findViewById(R.id.restaurant_hours);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(restaurantListInterface != null){
                                int pos = getAdapterPosition();

                                if(pos != RecyclerView.NO_POSITION){
                                    restaurantListInterface.onItemClick(pos);
                                }
                            }
                        }
                    }
            );

        }
    }
}
