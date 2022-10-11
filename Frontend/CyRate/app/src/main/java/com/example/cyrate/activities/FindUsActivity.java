package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cyrate.R;

public class FindUsActivity extends AppCompatActivity {
    TextView address, phoneNum, hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us);

        address = (TextView) findViewById(R.id.address_txt);
        phoneNum = (TextView) findViewById(R.id.phoneNumber_txt);
        hours = (TextView) findViewById(R.id.hours_txt);

        Bundle extras = getIntent().getExtras();

        String hoursString = extras.getString("HOURS").split("\\|")[0];


        address.setText(extras.get("ADDRESS").toString());
        phoneNum.setText("(515) 293-0180");
        hours.setText(hoursString);


    }
}