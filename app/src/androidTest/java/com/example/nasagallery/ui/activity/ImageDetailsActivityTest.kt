package com.example.nasagallery.ui.activity

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.viewpager2.widget.ViewPager2
import com.example.nasagallery.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageDetailsActivityTest{

    @get:Rule
    var rule: ActivityTestRule<ImageDetailsActivity> = ActivityTestRule(ImageDetailsActivity::class.java)

    @Test
    fun ensureViewPagerIsLoaded(){
        val activity: ImageDetailsActivity = rule.activity
        val viewById: View = activity.findViewById(R.id.imageDetails_vp)
        assert(viewById!=null)
        assert(viewById is ViewPager2)
    }

}