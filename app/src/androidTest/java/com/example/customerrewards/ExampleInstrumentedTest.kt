package com.example.customerrewards

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.customerrewards.Activity.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.customerrewards", appContext.packageName)
    }

    @Test
    fun`can_enter_account_number`(){
        onView(withId(R.id.accountNumber)).perform(typeText("1234"))
    }

    @Test
    fun`can_enter_subscript_channels`(){
        onView(withId(R.id.subscriptions)).perform(typeText("MUSIC,SPORTS"))
    }

    @Test
    fun`can_check_rewards`(){
        onView(withId(R.id.accountNumber)).perform(typeText("123"))
        onView(withId(R.id.subscriptions)).perform(typeText("MUSIC,SPORTS"))
        onView(withId(R.id.submitBnt)).perform(click())
        onView(withId(R.id.result)).check(matches(withText("[CHAMPIONS_LEAGUE_FINAL_TICKET, KARAOKE_PRO_MICROPHONE]")))
    }


}