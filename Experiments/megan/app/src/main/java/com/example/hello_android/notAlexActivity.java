package com.example.hello_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class notAlexActivity extends AppCompatActivity {

    Spinner mealSpinner;
    Button submitButton, goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_alex);

        mealSpinner = (Spinner) findViewById(R.id.mealOptionSpinner);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.meals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        mealSpinner.setAdapter(adapter);

        submitButton = (Button) findViewById(R.id.submitButton);
        goBackButton = (Button) findViewById(R.id.goBackButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(notAlexActivity.this, alexActivity.class));
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(notAlexActivity.this, MainActivity.class));
            }
        });

    }
}