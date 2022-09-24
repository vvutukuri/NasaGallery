package com.example.nasagallery.ui.activity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.nasagallery.R
import com.example.nasagallery.ui.adapters.ImagesListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class GalleryActivityTest{


    @get:Rule
    var rule: ActivityTestRule<GalleryActivity> = ActivityTestRule(GalleryActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun ensureRecyclerViewIsPresent() {
        val activity: GalleryActivity = rule.activity
        val viewById: View = activity.findViewById(R.id.images_rv)
        assert(viewById!=null)
        assert(viewById is RecyclerView)
    }

    @Test
    fun testRecyclerViewHasItems(){
        val activity: GalleryActivity = rule.activity
        val viewById: View = activity.findViewById(R.id.images_rv)
        assert(viewById!=null)
        assert(viewById is RecyclerView)
        var recyclerView = viewById as RecyclerView
        var adapter = recyclerView.adapter!!
        assert(adapter is ImagesListAdapter)
        assert(adapter.itemCount>0)
    }


    @Test
    fun testRecyclerViewItemClick(){
        onView(withId(R.id.images_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,clickOnViewChild(R.id.preview_iv)))
        onView(withId(R.id.imageDetails_vp)).check(matches(isDisplayed()))
    }


    private fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null
        override fun getDescription() = "Click on a child view with specified id."
        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }

}