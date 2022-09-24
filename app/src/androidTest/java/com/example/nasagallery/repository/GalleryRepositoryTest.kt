package com.example.nasagallery.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nasagallery.utils.Resource
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GalleryRepositoryTest {

    private lateinit var galleryRepository: GalleryRepository
    private lateinit var context: Context

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        galleryRepository=GalleryRepository(context);
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetJsonDataFromAssetsNotNull() {
        var result=galleryRepository.getImagesData()
        assertTrue(result!=null)
    }

    @Test
    fun testGetJsonDataFromAssets() {
        var result=galleryRepository.getImagesData()
        assertTrue(result is Resource.Success)
    }
}