package com.example.nasagallery.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nasagallery.databinding.ActivityImageDetailsBinding
import com.example.nasagallery.models.ImagesDataListItem
import com.example.nasagallery.ui.adapters.ImageDetailAdapter
import com.example.nasagallery.utils.OnImageLoadListener


class ImageDetailsActivity : AppCompatActivity(),OnImageLoadListener {

    private var imagesDataList: ArrayList<ImagesDataListItem?>? = null
    private lateinit var binding: ActivityImageDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportPostponeEnterTransition()
        imagesDataList = intent.getParcelableArrayListExtra<ImagesDataListItem>("ImagesList")
        var selectedPosition=intent.getIntExtra("SelectedPosition",0)
        var imagesDetailAdapter=ImageDetailAdapter(imagesDataList,this@ImageDetailsActivity,this)
        binding.imageDetailsVp.adapter=imagesDetailAdapter
        imagesDataList?.let {
            binding.imageDetailsVp.setCurrentItem(selectedPosition,false)
        }

    }

    override fun onImageLoadedSuccess() {
        supportStartPostponedEnterTransition()
    }

    override fun onImageLoadedFail() {
        supportStartPostponedEnterTransition()
    }

}