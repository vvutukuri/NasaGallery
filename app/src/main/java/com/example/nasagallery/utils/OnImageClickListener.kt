package com.example.nasagallery.utils

import androidx.appcompat.widget.AppCompatImageView

interface OnImageClickListener {
    fun onImageClick(position:Int,imageView:AppCompatImageView)
}