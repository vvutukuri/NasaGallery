package com.example.nasagallery

import com.example.nasagallery.ui.activity.GalleryActivity
import com.example.nasagallery.ui.activity.ImageDetailsActivity
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(GalleryActivity::class,ImageDetailsActivity::class)
class ActivityTestSuite