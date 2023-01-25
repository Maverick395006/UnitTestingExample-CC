package com.maverick.unittestingexample_cc

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    /**
     * To launch MainActivity before any Test
     */
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    /**
     * Access View
     * Action on View
     * Match [Action Result] with [Expected Result]
     */
    @Test
    fun testNextButton_returnNextQuote() {

        onView(withId(R.id.tvNext)).perform(click())
        onView(withId(R.id.tvNext)).perform(typeText("abc"))
        onView(withId(R.id.quoteText)).check(matches(withText("You can observe a lot just by watching.")))

    }

    @Test
    fun testShareButton_returnsIntentChooser() {
        Intents.init()
        val expected = allOf(hasAction(Intent.ACTION_SEND))
        onView(withId(R.id.floatingActionButton)).perform(click())
        intended(expected)
        Intents.release()
    }

}