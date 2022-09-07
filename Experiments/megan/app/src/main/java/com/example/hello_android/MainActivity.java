package com.example.hello_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button alexEat, notAlexEat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alexEat = (Button) findViewById(R.id.alexButton);
        notAlexEat = (Button) findViewById(R.id.notAlexButton);

        alexEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, alexActivity.class);
                startActivity(i);
            }
        });

        notAlexEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, notAlexActivity.class);
                startActivity(i);
            }
        });
    }

    public void imAlex(android.view.View view){

    }

    public void imNotAlex(android.view.View view){

    }

    //time 4:34 in video

}