package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.LoginActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.RegistrationFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    private static final int TEMP_USER_HEIGHT = 65;
    private static final int TEMP_USER_WEIGHT = 120;
    private static final int TEMP_USER_AGE = 20;

    private static final String TEMP_USER_FIRST_NAME = "Jane";
    private static final String TEMP_USER_LAST_NAME = "Doe";
    private static final String TEMP_USER_EMAIL = "janedoe@uw.edu";
    private static final String TEMP_USER_PASSWORD = "testing123";
    private static final String TEMP_USER_HEIGHT_STRING = "" + TEMP_USER_HEIGHT;
    private static final String TEMP_USER_WEIGHT_STRING = "" + TEMP_USER_WEIGHT;
    private static final String TEMP_USER_SEX = "Female";
    private static final String TEMP_USER_AGE_STRING = "" + TEMP_USER_AGE;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Test
    public void testRegister() {

        onView(withId(R.id.tv_login))
                .perform(click());

        Random random = new Random();
        //Generate an email address
        String email = "email" + (random.nextInt(10000) + 1)
                + (random.nextInt(900) + 1) + (random.nextInt(700) + 1)
                + (random.nextInt(400) + 1) + (random.nextInt(100) + 1)
                + "@uw.edu";

        // Type text and then press the button.
        onView(withId(R.id.text_fname))
                .perform(typeText(TEMP_USER_FIRST_NAME));
        onView(withId(R.id.text_lname))
                .perform(typeText(TEMP_USER_LAST_NAME));
        onView(withId(R.id.text_user_email))
                .perform(typeText(email));
        onView(withId(R.id.text_password))
                .perform(typeText(TEMP_USER_PASSWORD));
        onView(withId(R.id.text_height))
                .perform(typeText(TEMP_USER_HEIGHT_STRING));
        onView(withId(R.id.text_weight))
                .perform(typeText(TEMP_USER_WEIGHT_STRING));
        onView(withId(R.id.text_sex))
                .perform(typeText(TEMP_USER_SEX));
        onView(withId(R.id.text_age))
                .perform(typeText(TEMP_USER_AGE_STRING));
        onView(withId(R.id.btn_sign_up))
                .perform(click());

        onView(withText("Successfully Registered!"))
                .inRoot(withDecorView(not(is(
                        mActivityRule.getActivity()
                                .getWindow()
                                .getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void testRegisterInvalidEmail() {

        onView(withId(R.id.tv_login))
                .perform(click());

        // Type text and then press the button.
        onView(withId(R.id.text_fname))
                .perform(typeText(TEMP_USER_FIRST_NAME));
        onView(withId(R.id.text_lname))
                .perform(typeText(TEMP_USER_LAST_NAME));
        onView(withId(R.id.text_user_email))
                .perform(typeText("janedoeuw.edu"));
        onView(withId(R.id.text_password))
                .perform(typeText(TEMP_USER_PASSWORD));
        onView(withId(R.id.text_height))
                .perform(typeText(TEMP_USER_HEIGHT_STRING));
        onView(withId(R.id.text_weight))
                .perform(typeText(TEMP_USER_WEIGHT_STRING));
        onView(withId(R.id.text_sex))
                .perform(typeText(TEMP_USER_SEX));
        onView(withId(R.id.text_age))
                .perform(typeText(TEMP_USER_AGE_STRING));
        onView(withId(R.id.btn_sign_up))
                .perform(click());

        onView(withText("Please enter valid email address which contains an @ and a dot"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void testRegisterInvalidPassword() {

        onView(withId(R.id.tv_login))
                .perform(click());

        // Type text and then press the button.
        onView(withId(R.id.text_fname))
                .perform(typeText(TEMP_USER_FIRST_NAME));
        onView(withId(R.id.text_lname))
                .perform(typeText(TEMP_USER_LAST_NAME));
        onView(withId(R.id.text_user_email))
                .perform(typeText(TEMP_USER_EMAIL));
        onView(withId(R.id.text_password))
                .perform(typeText("t"));
        onView(withId(R.id.text_height))
                .perform(typeText(TEMP_USER_HEIGHT_STRING));
        onView(withId(R.id.text_weight))
                .perform(typeText(TEMP_USER_WEIGHT_STRING));
        onView(withId(R.id.text_sex))
                .perform(typeText(TEMP_USER_SEX));
        onView(withId(R.id.text_age))
                .perform(typeText(TEMP_USER_AGE_STRING));
        onView(withId(R.id.btn_sign_up))
                .perform(click());

        onView(withText("Enter valid password (at least 6 characters)"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(isDisplayed()));
    }
}