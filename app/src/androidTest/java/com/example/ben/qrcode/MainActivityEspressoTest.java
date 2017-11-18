package com.example.ben.qrcode;


import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;



/**
 * Created by Ben on 2017-10-05.
 */

public class MainActivityEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ViewInventory() {
        onView(withId(R.id.btn_inv)).perform(click());
        onView(withId(R.id.btn_back)).perform(click());
        //onView(withId(R.id.ListView)).check(matches(isDisplayed()));
    }

    @Test
    public void ViewSales() {
        onView(withId(R.id.btn_sales)).perform(click());
        onView(withId(R.id.btn_back)).perform(click());
        //onView(withId(R.id.ListView)).check(matches(isDisplayed()));
    }

    @Test
    public void CreateProduct() {
        onView(withId(R.id.btn_create)).perform(click());
        onView(withId(R.id.number)).perform(replaceText("2001"));
        onView(withId(R.id.number)).check(matches(withText("2001")));
        onView(withId(R.id.name)).perform(replaceText("Espresso Test"));
        onView(withId(R.id.name)).check(matches(withText("Espresso Test")));
        onView(withId(R.id.quantity)).perform(replaceText("200"));
        onView(withId(R.id.quantity)).check(matches(withText("200")));
        onView(withId(R.id.cost)).perform(replaceText("200"));
        onView(withId(R.id.cost)).check(matches(withText("200")));
        onView(withId(R.id.back)).perform(click());
    }
}

