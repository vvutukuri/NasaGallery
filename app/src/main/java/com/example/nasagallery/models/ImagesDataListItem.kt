package com.example.nasagallery.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesDataListItem(
    val copyright: String?,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
):Parcelable