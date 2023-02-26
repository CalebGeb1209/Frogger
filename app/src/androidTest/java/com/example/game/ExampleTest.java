package com.example.game;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class ExampleTest {
    @RunWith(AndroidJUnit4.class)
    public class NameDisplayTest {

        @Rule
        public ActivityScenarioRule<GameScreen> activityRule =
                new ActivityScenarioRule<>(GameScreen.class);

        @Test
        public void nameIsDisplayed() {
            onView(withId(R.id.name)).check(matches(isDisplayed()));
        }

    }
}
