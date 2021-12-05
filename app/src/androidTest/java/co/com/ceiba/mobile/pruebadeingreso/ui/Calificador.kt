package co.com.ceiba.mobile.pruebadeingreso.ui

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.com.ceiba.mobile.pruebadeingreso.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Calificador {


    @Rule @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun emptyTest() {
        Thread.sleep(4000)
        clickInputSearch()
        keypressInputSearch("empty")
        onView(allOf(withText("List is empty"))).check(matches(withText("List is empty")))
    }

    @Test
    fun ervinTest() {
        clickInputSearch()
        keypressInputSearch("Ervin")
        onView(allOf(withId(R.id.name))).check(matches(withText("Ervin Howell")))
        onView(allOf(withId(R.id.phone))).check(matches(withText("010-692-6593 x09125")))
        onView(allOf(withId(R.id.email))).check(matches(withText("Shanna@melissa.tv")))
    }

    @Test
    fun leanneTest() {
        clickInputSearch()
        keypressInputSearch("Leanne")
        onView(allOf(withId(R.id.name))).check(matches(withText("Leanne Graham")))
        onView(allOf(withId(R.id.phone))).check(matches(withText("1-770-736-8031 x56442")))
        onView(allOf(withId(R.id.email))).check(matches(withText("Sincere@april.biz")))
    }

    @Test
    @Throws(InterruptedException::class)
    fun leannePostClickVerPublicacionesTest() {
        clickInputSearch()
        keypressInputSearch("Leanne")
        onView(allOf(withId(R.id.btn_view_post))).perform(click())
        Thread.sleep(4000)
        onView(allOf(withId(R.id.name))).check(matches(withText(Matchers.endsWith("Leanne Graham"))))
        onView(allOf(withId(R.id.phone))).check(matches(withText("1-770-736-8031 x56442")))
        onView(
            allOf(
                withId(R.id.title),
                withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
            )
        ).check(matches(withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))
    }

    private fun keypressInputSearch(valueToWrite: String) {
        val appCompatEditText2: ViewInteraction = onView(
            allOf(
                withId(R.id.editTextSearch),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutSearch),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText(valueToWrite), closeSoftKeyboard())
    }

    private fun clickInputSearch() {
        val appCompatEditText: ViewInteraction = onView(
            allOf(
                withId(R.id.editTextSearch),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutSearch),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(click())
    }

    companion object {
        private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int
        ): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("Child at position $position in parent ")
                    parentMatcher.describeTo(description)
                }

                public override fun matchesSafely(view: View): Boolean {
                    val parent = view.parent
                    return (parent is ViewGroup && parentMatcher.matches(parent)
                            && view == parent.getChildAt(position))
                }
            }
        }
    }
}