package com.example.cyrate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    EditText email, password, confirmPassword, username;
    Button signUp;

    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);


        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        confirmPassword = root.findViewById(R.id.confirmPassword);
        signUp = root.findViewById(R.id.btn_signUp);
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
                        registerUser(view);
                        //check if email exists in database

                        //post new user

                        //on error, display error message

                        //set global user



                    }
                }));

        return root;
    }

    public void registerUser(View view){
        //get the data from the textboxes
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();
        String userUsername = username.getText().toString();

        UserLogic userLogic = new UserLogic();


        //look for email in database (GET user by email)

        userLogic.getUserByEmail(userEmail, new getUserByEmailResponse() {
            @Override
            public void onSuccess(UserModel userModel) {
                Toast.makeText(getActivity(), "sorry, this email is already in use", Toast.LENGTH_LONG).show();
                keepChecking = false;

                return;
            }

            @Override
            public void onError(String s) {
                Log.d("SUCCESS", "In onERROR (didnt find email)");
                Toast.makeText(getActivity(), "this email is good to go", Toast.LENGTH_SHORT).show();
                keepChecking = true;

                //confirm userPassword and userConfirmPassword are equal
                if (keepChecking){
                    if (!userPassword.equals(userConfirmPassword)){
                        Log.d("USERPASS", "Passwords Don't match");
                        Toast.makeText(getActivity(), "oops! passwords don't match!", Toast.LENGTH_SHORT).show();
                        keepChecking = false;
                        return;
                    }
                }

                // Check if username already used
                if(keepChecking){
                    Log.d("SIGNUP TAB FRAG", IntroActivity.usernamePasswordMap.toString());
                    if(IntroActivity.usernamePasswordMap.containsKey(userUsername) || userUsername == null){
                        Log.d("USERNAME", "Username exists");
                        keepChecking = false;
                        Toast.makeText(getActivity(), "username is unavailable", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

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
                                keepChecking = false;
                            }
                        }, "normal", userEmail, userPassword, userUsername);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //if email is in database, display a toast "this email is already registered", return false



        //if not, display a toast "passwords do not match"

        //if they do, create new userModel

        //POST new user to database


//        //get user by email
//        if (keepChecking) {
//            userLogic.getUserByEmail(userEmail, new getUserByEmailResponse() {
//                @Override
//                public void onSuccess(UserModel userModel) {
//                    //set global user
//                    MainActivity.globalUser = userModel;
//                }
//
//                @Override
//                public void onError(String s) {
//                    Toast.makeText(getActivity(), "oops: " + s, Toast.LENGTH_LONG).show();
//
//                }
//            });
//        }

    }
}
