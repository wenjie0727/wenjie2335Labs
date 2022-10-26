package algonquin.cst2335.jian0076;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        //find the view with id R.id.editText
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
//typing "12345" into that view, then close the keyboard
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

//find the button with id R.id.button
        ViewInteraction materialButton = onView(withId(R.id.button));
//perform clicking that button
        materialButton.perform(click());

//find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
//check the result of the text
        textView.check(matches(withText("You shall not pass!")));
    }
    /**
     * This tests the password missing upper case letter
     */
    @Test
    public void testFindMissingUpperCase() {
        //find the view with id R.id.editText
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //perform typing "wen123#" into that view
        appCompatEditText.perform(replaceText("wen123#"));

        //find the button with id R.id.button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //perform clicking that button
        materialButton.perform(click());

        //find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the result of the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This tests the password missing lower case letter
     */
    @Test
    public void testFindMissingLowerCase() {
        //find the view with id R.id.editText
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //perform typing "JWJ89#" into that view
        appCompatEditText.perform(replaceText("JWJ89#"));

        //find the button with id R.id.button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //perform clicking that button
        materialButton.perform(click());

        //find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the result of the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This tests the password missing special characters
     */
    @Test
    public void testFindMissingSpecialCharacter() {
        //find the view with id R.id.editText
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //perform typing "Wenjiejiang123" into that view
        appCompatEditText.perform(replaceText("Wenjiejiang123"));

        //find the button with id R.id.button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //perform clicking that button
        materialButton.perform(click());

        //find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the result of the text
        textView.check(matches(withText("You shall not pass!")));
    }
    /**
     * This tests the password meets requirements
     */
    @Test
    public void testMeetRequirements() {
        //find the view with id R.id.editText
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        //perform typing "Jwj123@" into that view
        appCompatEditText.perform(replaceText("Jwj123@"));

        //find the button with id R.id.button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //perform clicking that button
        materialButton.perform(click());

        //find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
        //check the result of the text
        textView.check(matches(withText("Your password meets the requirements")));
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
