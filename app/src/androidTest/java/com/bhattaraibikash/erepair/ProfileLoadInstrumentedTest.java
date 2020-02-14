package com.bhattaraibikash.erepair;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.bhattaraibikash.erepair.activities.MainActivity;
import com.bhattaraibikash.erepair.url.Url;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileLoadInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity>
            testRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void ProfileFragment(){
        testRule.getActivity().getSupportFragmentManager().beginTransaction();

        Url.token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTQ2ZjcwNGY2NTJhNTM2NjY4Mjk4N2MiLCJpYXQiOjE1ODE3MTI3Mjd9.0FAp7WjVM89nwKipQxzqZ-D5sGh4i_KB_t08nT2FoGE";
    }

    @Test
    public void checkUserProfileLoad() {
        onView(withId(R.id.navProfile)).perform(click());

        onView(withId(R.id.tvUsernameProfile)).check(matches(withText("username")));
    }
}
