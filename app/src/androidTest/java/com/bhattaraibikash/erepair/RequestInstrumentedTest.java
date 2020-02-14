package com.bhattaraibikash.erepair;

import androidx.test.rule.ActivityTestRule;

import com.bhattaraibikash.erepair.activities.info.ProfessionalActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RequestInstrumentedTest {
    @Rule
    public ActivityTestRule<ProfessionalActivity>
            testRule = new ActivityTestRule<>(ProfessionalActivity.class);

    @Test
    public void checkBecomeProfessional() {
        onView(withId(R.id.etNamePro)).perform(typeText("Milan Dhakal"), closeSoftKeyboard());
        onView(withId(R.id.etEmailPro)).perform(typeText("milanthhbapaaa@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etAddressPro)).perform(typeText("Jorpati"), closeSoftKeyboard());
        onView(withId(R.id.etPhonePro)).perform(typeText("9045414149"), closeSoftKeyboard());
        onView(withId(R.id.etSkillPro)).perform(typeText("Car Repair, Bike Repair"), closeSoftKeyboard());
        onView(withId(R.id.btnRequest)).perform(click());

        onView(withId(R.id.llBecomeProfessional)).check(matches(isDisplayed()));

    }
}
