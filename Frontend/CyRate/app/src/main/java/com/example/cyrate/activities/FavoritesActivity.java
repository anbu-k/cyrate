package com.example.cyrate.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cyrate.models.RecyclerViewInterface;

public class FavoritesActivity extends AppCompatActivity implements RecyclerViewInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(int position) {

    }
}
