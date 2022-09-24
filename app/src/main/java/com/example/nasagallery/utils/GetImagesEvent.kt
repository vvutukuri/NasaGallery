package com.example.nasagallery.utils

import com.example.nasagallery.models.ImagesDataListItem

sealed class GetImagesEvent{
    class Success(val imagesListData: List<ImagesDataListItem>) : GetImagesEvent()
    class Failure(val errorText: String) : GetImagesEvent()
}
