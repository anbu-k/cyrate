package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cyrate.R;
import com.example.cyrate.models.UserModel;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //current user. set at log in or sign up.
    public static UserModel globalUser;

    public static Map<Integer, Integer> fidToBid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}