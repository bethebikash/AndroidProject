package com.bhattaraibikash.erepair;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.bhattaraibikash.erepair.activities.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpInstrumentedTest {

    @Rule
    public ActivityTestRule<RegisterActivity>
            testRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void checkUserLogin() {
        onView(withId(R.id.etName)).perform(typeText("Bikash Test"), closeSoftKeyboard());
        onView(withId(R.id.etEmail)).perform(typeText("test122@testing.com"), closeSoftKeyboard());
        onView(withId(R.id.etAddress)).perform(typeText("Test Address"), closeSoftKeyboard());
        onView(withId(R.id.etPhone)).perform(typeText("9854541241"), closeSoftKeyboard());
        onView(withId(R.id.etUsernameReg)).perform(typeText("usertest11555"), closeSoftKeyboard());
        onView(withId(R.id.etPasswordReg)).perform(typeText("Admin123"), closeSoftKeyboard());
        onView(withId(R.id.btnSighUp)).perform(click());

        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()));

    }
}
