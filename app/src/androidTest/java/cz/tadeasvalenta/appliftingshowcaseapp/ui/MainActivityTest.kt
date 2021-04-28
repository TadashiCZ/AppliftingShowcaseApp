package cz.tadeasvalenta.appliftingshowcaseapp.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import cz.tadeasvalenta.appliftingshowcaseapp.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appCompatImageView = onView(
            allOf(
                withId(R.id.search_button), withContentDescription("Search"),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("c101"), closeSoftKeyboard())

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.search_close_btn), withContentDescription("Clear query"),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.btnLandpads), withText("Landpads"),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val appCompatImageView3 = onView(
            allOf(
                withId(R.id.search_button),
                withContentDescription("Search"),
                isDisplayed()
            )
        )
        appCompatImageView3.perform(click())

        val searchAutoComplete2 = onView(allOf(withId(R.id.search_src_text), isDisplayed()))
        searchAutoComplete2.perform(replaceText("asog"), closeSoftKeyboard())

        val recyclerView = onView(allOf(withId(R.id.rvList)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView2 = onView(
            allOf(
                withId(R.id.tvFullName), withText("A Shortfall of Gravitas"),
                withParent(
                    allOf(
                        withId(R.id.clLandpad),
                        withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("A Shortfall of Gravitas")))

        val materialButton2 = onView(
            allOf(
                withId(R.id.tvWikipedia),
                withText("Open Wikipedia article"),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
    }
}
