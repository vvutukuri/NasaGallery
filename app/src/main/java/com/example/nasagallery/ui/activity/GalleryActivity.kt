package com.example.nasagallery.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nasagallery.databinding.ActivityGalleryBinding
import com.example.nasagallery.models.ImagesDataListItem
import com.example.nasagallery.ui.adapters.ImagesListAdapter
import com.example.nasagallery.utils.GetImagesEvent
import com.example.nasagallery.utils.OnImageClickListener
import com.example.nasagallery.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GalleryActivity : AppCompatActivity(),OnImageClickListener {
    private lateinit var imagesListAdapter: ImagesListAdapter
    private lateinit var binding: ActivityGalleryBinding
    private val galleryViewModel:GalleryViewModel by lazy {
        ViewModelProvider(this)[GalleryViewModel::class.java]
    }
    private var imagesList = ArrayList<ImagesDataListItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imagesRv.layoutManager=GridLayoutManager(this,3)

        imagesListAdapter= ImagesListAdapter(imagesList,this@GalleryActivity,this)
        binding.imagesRv.adapter=imagesListAdapter
        galleryViewModel.getImagesListData()
        galleryViewModel.imagesList.observe(this@GalleryActivity, Observer {
            when(it){
                is GetImagesEvent.Success ->{
                    var imagesDataList=it.imagesListData.sortedByDescending { it.date }
                    imagesList.clear()
                    imagesList.addAll(imagesDataList)
                    imagesListAdapter.notifyDataSetChanged()
                }
                is GetImagesEvent.Failure ->{
                    Toast.makeText(this@GalleryActivity,"Something went wrong,please try again.",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onImageClick(position: Int,imageView:AppCompatImageView) {
        var intent=Intent(this@GalleryActivity, ImageDetailsActivity::class.java)
        intent.putParcelableArrayListExtra("ImagesList",imagesList)
        intent.putExtra("SelectedPosition",position)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@GalleryActivity, imageView, imageView.transitionName)
        startActivity(intent,options.toBundle())
    }
}