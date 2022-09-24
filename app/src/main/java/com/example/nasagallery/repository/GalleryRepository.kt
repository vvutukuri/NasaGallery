package com.example.nasagallery.repository

import android.content.Context
import com.example.nasagallery.models.ImagesDataListItem
import com.example.nasagallery.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class GalleryRepository(private val context: Context) {

    fun getImagesData():Resource<List<ImagesDataListItem>>{
        lateinit var jsonString: String
        return try {
            jsonString = context.assets.open("data.json")
                .bufferedReader()
                .use { it.readText() }
            val imagesDataList = object : TypeToken<List<ImagesDataListItem>>() {}.type
            Resource.Success(Gson().fromJson(jsonString, imagesDataList))
        } catch (exception: Exception) {
            exception.printStackTrace()
            Resource.Error(exception.localizedMessage)
        }

    }
}