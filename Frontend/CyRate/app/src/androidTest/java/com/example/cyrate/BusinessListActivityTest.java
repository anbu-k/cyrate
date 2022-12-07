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
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.cyrate.activities.BusinessListActivity;
import com.example.cyrate.activities.IndividualBusinessActivity;
import com.example.cyrate.activities.IntroActivity;
import com.example.cyrate.activities.LoginActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


// Mock the RequestServerForService class
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest   // large execution time
public class BusinessListActivityTest {

    @Rule   // needed to launch the activity
    public ActivityTestRule<BusinessListActivity> activityRule = new ActivityTestRule<>(BusinessListActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown(){
        Intents.release();
    }

    @Test
    public void selectingBusinessNavigatesToIndividualBusiness(){
        activityRule.launchActivity(new Intent());

        // Wait for buttons to animate in
        onView(isRoot()).perform(TestUtils.waitFor(2000));

        onView(withId(R.id.restaurantList_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(isRoot()).perform(TestUtils.waitFor(200));

        // Verify we navigate to the next activity
        intended(hasComponent(IndividualBusinessActivity.class.getName()));

        // Our first restaurant is The Cafe, verify that the name matches
        onView(withId(R.id.restaurant_name)).check(matches(withText("The Cafe")));
    }

    @Test
    public void openNavMenu(){
        onView(withId(R.id.open_menu_icon)).perform(click());
        onView(withId(R.id.nav_menu_header)).check(matches(isDisplayed()));
    }

    @Test
    public void navMenuSignInSelect(){
        activityRule.launchActivity(new Intent());

        // Open the Nav Menu and click Sign In
        onView(withId(R.id.open_menu_icon)).perform(click());
        onView(withId(R.id.nav_menu_header)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_sign_in)).perform(click());

        // Verify we navigate back to the Login Activity
        intended(hasComponent(LoginActivity.class.getName()));
    }



}



