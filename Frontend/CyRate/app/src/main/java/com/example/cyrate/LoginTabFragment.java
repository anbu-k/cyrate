package com.example.cyrate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cyrate.activities.WelcomeToCyRateActivity;

public class LoginTabFragment extends Fragment {

    EditText email, password;
    TextView forgotPass;
    Button login;

    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        forgotPass = root.findViewById(R.id.forgotPassword);
        login = root.findViewById(R.id.btn_login);

        email.setTranslationX(800);
        password.setTranslationX(800);
        forgotPass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgotPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get user by email

                //verify password

                //set global user

                Intent i = new Intent(getActivity(), WelcomeToCyRateActivity.class);
                startActivity(i);
            }
        }));
        return root;

    }
}
