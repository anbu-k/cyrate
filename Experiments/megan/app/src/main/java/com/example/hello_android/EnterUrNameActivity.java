package com.example.hello_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class EnterUrNameActivity extends AppCompatActivity {
    EditText nameTextbox;
    Button submitButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ur_name);

        nameTextbox = (EditText) findViewById(R.id.usersName);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                String name = nameTextbox.getText().toString();
                if(name.toLowerCase(Locale.ROOT).equals("alex")){
                    i = new Intent(EnterUrNameActivity.this, alexActivity.class);
                }
                else{
                    i = new Intent(EnterUrNameActivity.this, notAlexActivity.class);
                }
                Log.d("name:",name);
                Log.d("intent info: ", i.toString());
//                i.putExtra("name", name);
                startActivity(i);
            }
        }));

    }
}
