package com.wisdomlanna.www.dagger2_mvp_example.main;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wisdomlanna.www.dagger2_mvp_example.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends BaseTestServer {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity
            = new ActivityTestRule<>(MainActivity.class, true, false);

    @Test
    public void validateResultRetrofit() throws IOException {
        getServer().enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(getDataFromFile("user.json")));

        mainActivity.launchActivity(null);

        onView(withId(R.id.tvUsername))
                .check(matches(withText("Jedsada Tiwongvorakul")));
    }

    @Override
    void setBefore() {

    }

    @Override
    void setAfter() {

    }
}