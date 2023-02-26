package com.example.game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Unit Tests for Sprint 2, which will execute on an Android device.
 *
 * TODO: There are plenty of trivial tests to do if you don't have time
 * to create good ones.
 * (1) tests to make sure that all of the buttons are clickable
 * you can do this by looking at isisRedSpriteClickable() below and following that.
 * Doing (1) will give 5 total unit tests, all super easy to implement
 *
 * (2) Also, you can make sure the text displayed on the config screen is correct.
 * make sure that "Choose Your Character" and "Choose Your Name" is displayed.
 * Doing (1) and (2) makes 7 unit tests.
 *
 * (3) Make sure that the pixelheart images are displayed and that they are
 * positioned correctly. To check the relative positioning of a drawable or item by id
 * check the link below:
 * @see <a href="https://developer.android.com/training/testing/espresso/cheat-sheet">Espresso Cheat Sheet</a>
 *
 * Between (1), (2), and (3) there are 10-11 available tests to make.
 *
 * NOTE: someone can take credit for 1 of the 3 tests already created below,
 * since we all only need 2
 *
 *
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SprintTwoUnitTests {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.game", appContext.getPackageName());
    }
//    @Rule
//    public ActivityScenarioRule<WelcomeScreen> welcomeRule =
//            new ActivityScenarioRule<>(WelcomeScreen.class);
    @Rule
    public ActivityScenarioRule<ConfigScreen> configRule =
            new ActivityScenarioRule<>(ConfigScreen.class);

    /**
     * Test to check if red sprite is clickable
     *
     */
    @Test
    // Harris
    public void isRedSpriteClickable() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("User Name"));

        onView(withId(R.id.imageButton9)).perform(click());
        onView(withId(R.id.imageButton9)).check(matches(isClickable()));
    }

    /**
     * Test to check if green sprite is clickable.
     */
    @Test
    public void isGreenSpriteClickable() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("User Name"));

        onView(withId(R.id.imageButton10)).perform(click());
        onView(withId(R.id.imageButton10)).check(matches(isClickable()));
    }
    /**
     * Test to check if blue sprite is clickable
     *
     */
    @Test
    //Yaphet
    public void isBlueSpriteClickable() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("User Name"));

        onView(withId(R.id.imageButton10)).perform(click());
        onView(withId(R.id.imageButton10)).check(matches(isClickable()));
    }

    /**
     * Test to check if name is displayed correctly given a valid input
     *
     */
    @Test
    // Harris
    public void validNameTest() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("User Name"));
        onView(withId(R.id.editTextTextPersonName)).check(matches(withText("User Name")));
    }

    /**
     * Checks if name text is displayed on config screen
     */
    @Test
    // Srihith
    public void isNameTextDisplayed() {
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
    }

    /**
     * Checks if difficulty text is displayed on config screen
     */
    @Test
    // Srihith
    public void isDifficultyTextDisplayed() {
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
    }

    /**
     * Checks if character choice text is displayed on config screen
     */
    @Test
    public void isCharacterTextDisplayed() {
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }
    /**
     * Test to check if user can advance to next screen with invalid white space name
     *
     */
    @Test
    public void invalidNameTest() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText(" "));

        // Perform a click on a button to trigger some action in the app
        onView(withId(R.id.button)).check(matches(isNotClickable()));
    }

    /**
     * Test to check if user has 3 lives based on chosen difficulty easy
     *
     */
    @Test
    //Sena Korkaya
    public void difficultyEasyTest() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("User Name"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.button3)).perform(click());

        onView(withId(R.id.imageButton10)).perform(click());

        onView(withId(R.id.imageView2)).check(matches(isDisplayed()));
   }

    /**
     * Test to check if user has 2 lives based on chosen difficulty medium
     *
     */
    @Test
    //Sena Korkaya
    public void difficultyMediumTest() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("User Name"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.imageButton10)).perform(click());

        onView(withId(R.id.imageView5)).check(matches(isDisplayed()));
    }

    /**
     * Test to check if user has 1 life based on chosen difficulty hard
     *
     */
    @Test
    //Sena Korkaya
    public void difficultyHardTest() {
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("User Name"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.button4)).perform(click());

        onView(withId(R.id.imageButton10)).perform(click());

        onView(withId(R.id.imageView6)).check(matches(isDisplayed()));
    }

    /**
     * Test to ensure that the game screen is displayed when the
     * "Start Game" button is clicked on the configuration screen.
     */
    //Yaphet
    @Test
    public void startGameTest() {
        onView(withId(R.id.editTextTextPersonName)).perform(typeText("User Name"));
        onView(withId(R.id.imageButton9)).perform(click());
        onView(withId(R.id.button2)).check(matches(isClickable()));
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }



}