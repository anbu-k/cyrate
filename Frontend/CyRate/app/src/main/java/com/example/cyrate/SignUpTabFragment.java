package com.example.cyrate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cyrate.Logic.UserLogic;
import com.example.cyrate.Logic.getAllUsersResponse;
import com.example.cyrate.activities.BusinessListActivity;
import com.example.cyrate.models.UserModel;

import java.util.List;

public class SignUpTabFragment extends Fragment {
    EditText email, password, confirmPassword;
    Button signUp;

    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);


        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        confirmPassword = root.findViewById(R.id.confirmPassword);
        signUp = root.findViewById(R.id.btn_signUp);

        email.setTranslationX(800);
        password.setTranslationX(800);
        confirmPassword.setTranslationX(800);
        signUp.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        confirmPassword.setAlpha(v);
        signUp.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirmPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        signUp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        signUp.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               UserLogic userLogic = new UserLogic();
               userLogic.getUser(new getAllUsersResponse() {
                   @Override
                   public void onSuccess(List<UserModel> list) {
                       Toast.makeText(getActivity(), list.toString(), Toast.LENGTH_LONG).show();
                   }

                   @Override
                   public void onError(String s) {
                       Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();

                   }
               });
            }
        }));

        return root;
    }

    public boolean registerUser(View view){
        //get the data from the textboxes
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();

        //look for email in database (GET user by email)

        //if email is in database, display a toast "this email is already registered", return false

        //confirm userPassword and userConfirmPassword are equal

        //if not, display a toast "passwords do not match"

        //if they do, create new userModel

        //POST new user to database

        //return true


        return true;
    }
}
