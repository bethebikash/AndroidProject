package com.bhattaraibikash.erepair;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.bhattaraibikash.erepair.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MapInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity>
            testRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void infoFragment(){
        testRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void checkMap() {
        onView(withId(R.id.navInfo)).perform(click());
        onView(withId(R.id.llContactInfo)).perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }
}
