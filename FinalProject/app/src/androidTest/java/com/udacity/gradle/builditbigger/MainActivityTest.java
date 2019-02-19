package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void getJokeTest() {
        String joke = null;
        try {
            joke = new EndpointsAsyncTask().execute(new Pair<Context, String>(mActivityTestRule.getActivity(), "")).get();
        } catch (Exception e) {
            fail("Error : " + e.getLocalizedMessage());
        }
        assertThat(joke, is(notNullValue()));
        assertThat(joke, is(not(isEmptyString())));

    }
}