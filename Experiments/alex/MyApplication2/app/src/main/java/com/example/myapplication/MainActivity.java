package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hello = findViewById(R.id.btn_clickme);
        TextView txt = findViewById(R.id.text_intro);

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "toastin", Toast.LENGTH_LONG).show();


                txt.setText(":sighs: I have to sit by megan. gross");
                hello.setEnabled(false);
                hello.setText(":sighs: disabled");

            }
        });
    }
}