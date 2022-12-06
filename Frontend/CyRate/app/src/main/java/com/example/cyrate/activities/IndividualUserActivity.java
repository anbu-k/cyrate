package com.example.cyrate.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cyrate.ImageLoaderTask;
import com.example.cyrate.R;
import com.example.cyrate.UserType;

public class IndividualUserActivity extends AppCompatActivity {
    Button deleteUserBtn, editUserTypeButton;
    ImageView backBtn, profilePic;
    TextView userName, userEmail;

    String email, password, fullName, username, phoneNum, dob, photoUrl;
    UserType userType;
    int userId;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_user);

        extras = getIntent().getExtras();

        //get the extras
        email = extras.getString("EMAIL");
        System.out.println(email);
        password = extras.getString("PASSWORD");
        fullName = extras.getString("FULLNAME");
        username = extras.getString("USERNAME");
        phoneNum = extras.getString("PHONENUM");
        dob = extras.getString("DOB");
        photoUrl = extras.getString("PHOTOURL");

        userType = UserType.fromString(extras.getString("USERTYPE"));
        userId = extras.getInt("USERID");

        //get ui elements
        backBtn = (ImageView) findViewById(R.id.back_button_image);
        profilePic = (ImageView) findViewById(R.id.profile_pic);
        deleteUserBtn = (Button) findViewById(R.id.btn_delete_user);
        editUserTypeButton = (Button) findViewById(R.id.btn_edit_user);

        //profile pic
        new ImageLoaderTask(photoUrl, profilePic).execute();

        //user type dropdown


        //set user info ui elements
        String nameToDisplay = username;
        //if user's full name isn't empty, display that instead
        if (fullName != null && fullName.length() > 0){
            nameToDisplay = fullName;
        }
        userName.setText(nameToDisplay);
        userEmail.setText(email);



        //deal with button clicks
    }
}
