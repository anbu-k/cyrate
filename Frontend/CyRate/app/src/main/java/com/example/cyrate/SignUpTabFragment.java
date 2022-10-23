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
import com.example.cyrate.Logic.addUserResponse;
import com.example.cyrate.Logic.getAllUsersResponse;
import com.example.cyrate.Logic.getUserByEmailResponse;
import com.example.cyrate.activities.BusinessListActivity;
import com.example.cyrate.activities.IntroActivity;
import com.example.cyrate.activities.MainActivity;
import com.example.cyrate.activities.WelcomeActivity;
import com.example.cyrate.models.UserModel;

import org.json.JSONException;

import java.util.List;

public class SignUpTabFragment extends Fragment {
    public static boolean keepChecking = false;

    EditText email, password, confirmPassword, username, phoneNum;
    Button signUp;

    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);


        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        confirmPassword = root.findViewById(R.id.confirmPassword);
        signUp = root.findViewById(R.id.btn_signUp);
        phoneNum = root.findViewById(R.id.phoneNum);
        username = root.findViewById(R.id.username);

        email.setTranslationX(800);
        password.setTranslationX(800);
        confirmPassword.setTranslationX(800);
        signUp.setTranslationX(800);
        username.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        confirmPassword.setAlpha(v);
        signUp.setAlpha(v);
        username.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirmPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        signUp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();
        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        signUp.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent i = new Intent(getActivity(), WelcomeActivity.class);
//                        startActivity(i);
                        registerUser(view);
                        //check if email exists in database

                        //post new user

                        //on error, display error message

                        //set global user



                    }
                }));

        return root;
    }

    public boolean registerUser(View view){
        //get the data from the textboxes
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();
        String userUsername = username.getText().toString();
        String userPhonenum = phoneNum.getText().toString();

        UserLogic userLogic = new UserLogic();


        //look for email in database (GET user by email)

        userLogic.getUserByEmail(userEmail, new getUserByEmailResponse() {
            @Override
            public void onSuccess(UserModel userModel) {
                Toast.makeText(getActivity(), "sorry, this email is already in use", Toast.LENGTH_LONG).show();
                keepChecking = false;
            }

            @Override
            public void onError(String s) {
                Toast.makeText(getActivity(), "this email is good to go", Toast.LENGTH_LONG).show();

                UserLogic.getAllUsers(new getAllUsersResponse() {
                    @Override
                    public void onSuccess(List<UserModel> list) {
                        for (int i = 0; i < list.size(); i++) {
                            if ((list.get(i).getUsername() != null) && (list.get(i).getUsername().equals(userUsername))) {
                                keepChecking = false;
                                Toast.makeText(getActivity(), "username is unavailable", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (keepChecking){
                                if (!userPassword.equals(userConfirmPassword)){
                                    Toast.makeText(getActivity(), "oops! passwords don't match!", Toast.LENGTH_LONG).show();
                                    keepChecking = false;
                                }
                        }
                    }

                    @Override
                    public void onError(String s) {
                        Toast.makeText(getActivity(), "uh oh in check username", Toast.LENGTH_LONG).show();

                    }
                });

                keepChecking = true;
            }
        });

        keepChecking = true;

        //if email is in database, display a toast "this email is already registered", return false

        //confirm userPassword and userConfirmPassword are equal
//        if (keepChecking){
//            if (!userPassword.equals(userConfirmPassword)){
//                Toast.makeText(getActivity(), "oops! passwords don't match!", Toast.LENGTH_LONG).show();
//                keepChecking = false;
//            }
//        }

        //check username
        //TODO we don't have a good way to check if the username exists in the db.
        //since it's unique this is important
        //for now just loop through all users ig
        if (keepChecking) {
            UserLogic.getAllUsers(new getAllUsersResponse() {
                @Override
                public void onSuccess(List<UserModel> list) {
                    for (int i = 0; i < list.size(); i++) {
                        if ((list.get(i).getUsername() != null) && (list.get(i).getUsername().equals(userUsername))) {
                            keepChecking = false;
                            Toast.makeText(getActivity(), "username is unavailable", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onError(String s) {
                    Toast.makeText(getActivity(), "uh oh in check username", Toast.LENGTH_LONG).show();

                }
            });
        }



        //if not, display a toast "passwords do not match"

        //if they do, create new userModel

        //POST new user to database

        if (keepChecking) {
            try {
                userLogic.addUser(new addUserResponse() {

                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getActivity(), WelcomeActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onError(String s) {
                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                        keepChecking = true;
                    }
                }, "normal", userEmail, userPassword, userUsername);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //get user by email
        if (keepChecking) {
            userLogic.getUserByEmail(userEmail, new getUserByEmailResponse() {
                @Override
                public void onSuccess(UserModel userModel) {
                    //set global user
                    MainActivity.globalUser = userModel;
                }

                @Override
                public void onError(String s) {
                    Toast.makeText(getActivity(), "oops: " + s, Toast.LENGTH_LONG).show();

                }
            });
        }



        //return true
        if (!keepChecking){
            return false;
        }

        return true;
    }
}
