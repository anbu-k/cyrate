package com.example.cyrate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;




import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


import android.content.Intent;
import android.os.Handler;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.cyrate.activities.IntroActivity;
import com.example.cyrate.activities.LoginActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


// Mock the RequestServerForService class
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest   // large execution time
public class LoginActivityTest {

    @Rule   // needed to launch the activity
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void guestButtonNavigatesToRestaurantActivity(){

        // Wait for buttons to animate in
        onView(isRoot()).perform(waitFor(2000));

        onView(withId(R.id.btn_use_as_guest)).perform(click());
        onView(isRoot()).perform(waitFor(200));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void loginClickWithEmptyFields(){
        final String expectedToastString = "Email is incorrect";

        Intents.init();
        activityRule.launchActivity(new Intent());


        // Wait for buttons to animate in
        onView(isRoot()).perform(waitFor(2000));

        // Do a click anc check that we're still on the login page (failed login)
        // Checking for Toast messages seems not to work for Espresso for SDK 30+
        // So this is the best way to check for now
        onView(withId(R.id.btn_login)).perform(click());

        intended(hasComponent(LoginActivity.class.getName()));

        // Tear down stuff, only needed for this test
        Intents.release();
    }

    // Allows us to set a delay in case we need to wait for a view to animate or appear on screen
    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}



