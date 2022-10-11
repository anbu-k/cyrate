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
import com.example.cyrate.Logic.getUserByEmailResponse;
import com.example.cyrate.activities.IntroActivity;
import com.example.cyrate.activities.MainActivity;
import com.example.cyrate.activities.WelcomeToCyRateActivity;
import com.example.cyrate.models.UserModel;

import org.json.JSONException;

public class SignUpTabFragment extends Fragment {
    public static boolean keepChecking = false;

    EditText email, password, confirmPassword, username, phoneNumber, dateOfBirth;
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
        phoneNumber = root.findViewById(R.id.phoneNumber);
        dateOfBirth = root.findViewById(R.id.dateOfBirth);


        email.setTranslationX(800);
        password.setTranslationX(800);
        confirmPassword.setTranslationX(800);
        signUp.setTranslationX(800);
        username.setTranslationX(800);
        phoneNumber.setTranslationX(800);
        dateOfBirth.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        confirmPassword.setAlpha(v);
        signUp.setAlpha(v);
        username.setAlpha(v);
        phoneNumber.setAlpha(v);
        dateOfBirth.setAlpha(v);


        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirmPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        signUp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();
        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        phoneNumber.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1000).start();
        dateOfBirth.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1050).start();


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

    public void registerUser(View view) {

        // Check if any inputs are not field
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty() ||
                username.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || dateOfBirth.getText().toString().isEmpty()
        ) {
            Toast.makeText(getActivity(), "Complete All Inputs", Toast.LENGTH_LONG).show();
        } else {

            //get the data from the textboxes
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            String userConfirmPassword = confirmPassword.getText().toString();
            String userUsername = username.getText().toString();
            String userPhoneNumber = phoneNumber.getText().toString();
            String userDateOfBirth = dateOfBirth.getText().toString();


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
                            if (keepChecking) {
                                if (!userPassword.equals(userConfirmPassword)) {
                                    Log.d("USERPASS", "Passwords Don't match");
                                    Toast.makeText(getActivity(), "oops! passwords don't match!", Toast.LENGTH_SHORT).show();
                                    keepChecking = false;
                                    return;
                                }
                            }

                            // Check if username already used
                            if (keepChecking) {
                                Log.d("SIGNUP TAB FRAG", IntroActivity.usernamesSet.toString());
                                if (IntroActivity.usernamesSet.contains(userUsername) || userUsername == null) {
                                    Log.d("USERNAME", "Username exists");
                                    keepChecking = false;
                                    Toast.makeText(getActivity(), "username is unavailable", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }

                            // Check if phone number already used
                            if (keepChecking) {
                                Log.d("SIGNUP TAB FRAG", IntroActivity.phoneNumberSet.toString());
                                if (IntroActivity.phoneNumberSet.contains(userPhoneNumber) || userPhoneNumber == null) {
                                    Log.d("PHONE NUMBER", "Phone Number exists");
                                    keepChecking = false;
                                    Toast.makeText(getActivity(), "Phone number already used!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }

                            if (keepChecking) {
                                try {
                                    userLogic.addUser(new addUserResponse() {

                                        @Override
                                        public void onSuccess(String s) {
                                            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                                            userLogic.getUserByEmail(userEmail, new getUserByEmailResponse() {
                                                @Override
                                                public void onSuccess(UserModel userModel) {
                                                    //set global user
                                                    MainActivity.globalUser = userModel;
                                                    Intent i = new Intent(getActivity(), WelcomeToCyRateActivity.class);
                                                    startActivity(i);
                                                }

                                                @Override
                                                public void onError(String s) {
                                                    Log.d("ERROR", "Error in getUserByEmail -> " + s);
                                                    Toast.makeText(getActivity(), "oops: " + s, Toast.LENGTH_LONG).show();

                                                }
                                            });

                                        }

                                        @Override
                                        public void onError(String s) {
                                            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                                            keepChecking = false;
                                        }
                                    }, "normal", userEmail, userPassword, userUsername, userPhoneNumber, userDateOfBirth);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
            );

            //if email is in database, display a toast "this email is already registered", return false


            //if not, display a toast "passwords do not match"

            //if they do, create new userModel

            //POST new user to database

        }
    }
}
