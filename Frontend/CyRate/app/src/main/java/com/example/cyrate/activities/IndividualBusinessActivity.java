package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cyrate.R;


public class IndividualBusinessActivity extends AppCompatActivity {

    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_business);

        back_btn = (ImageView)findViewById(R.id.back_button_image);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateBack();
            }
        });
    }

    private void navigateBack(){
        Intent intent = new Intent(this, BusinessListActivity.class);
        startActivity(intent);
    }
}