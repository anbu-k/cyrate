package com.example.cyrate.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cyrate.R;

public class EditProfileActivity extends AppCompatActivity {
    //declare variables
    EditText usernameText;
    EditText emailText;
    EditText passwordText;
    EditText nameText;
    EditText birthdayText;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set declared variables
        usernameText = findViewById(R.id.edittext_email);
        emailText = findViewById(R.id.edittext_email);
        passwordText = findViewById(R.id.edittext_password);
        nameText = findViewById(R.id.edittext_name);
        birthdayText = findViewById(R.id.edittext_birthday);
        updateButton = findViewById(R.id.btn_update);

        updateButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));

    }
}
