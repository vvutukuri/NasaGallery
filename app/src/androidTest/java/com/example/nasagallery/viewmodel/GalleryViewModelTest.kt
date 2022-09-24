package com.example.nasagallery.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nasagallery.getOrAwaitValue
import com.example.nasagallery.repository.GalleryRepository
import com.example.nasagallery.utils.GetImagesEvent
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GalleryViewModelTest{
    private lateinit var viewModel: GalleryViewModel
    private lateinit var context:Context

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        context=ApplicationProvider.getApplicationContext<Context>()
        viewModel= GalleryViewModel(GalleryRepository(context))
    }


    @Test
    fun testLiveDataNotNull(){
        viewModel.getImagesListData()
        var result = viewModel.imagesList.getOrAwaitValue()
        assertTrue(result !=null)
    }

    @Test
    fun testLiveDataSuccessEvent(){
        viewModel.getImagesListData()
        var result = viewModel.imagesList.getOrAwaitValue()
        assertTrue(result is GetImagesEvent.Success)
    }
}