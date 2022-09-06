package com.example.hello_android;

import androidx.appcompat.app.AppCompatActivity;
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

        alexEat = (Button) findViewById(R.id.forAlex);
        notAlexEat = (Button) findViewById(R.id.forEveryoneElse);

        alexEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "hi alex, looking for ur dinner", Toast.LENGTH_LONG).show();
            }
        });

        notAlexEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "loading your dinner...", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void imAlex(android.view.View view){

    }

    public void imNotAlex(android.view.View view){

    }

    //time 4:34 in video

}