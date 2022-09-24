package com.example.nasagallery.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nasagallery.R
import com.example.nasagallery.databinding.GalleryImageItemBinding
import com.example.nasagallery.models.ImagesDataListItem
import com.example.nasagallery.utils.OnImageClickListener


class ImagesListAdapter(
    private val mList: ArrayList<ImagesDataListItem>,
    private val context: Context,
    private val onImageClickListener: OnImageClickListener
) : RecyclerView.Adapter<ImagesListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            GalleryImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val options: RequestOptions = RequestOptions()
                .centerInside()
                .placeholder(R.drawable.ic_img_place_holder)
                .error(R.drawable.ic_img_place_holder)
            Glide.with(context).load(mList[position].url).apply(options).into(binding.previewIv)
            binding.previewIv.setOnClickListener {
                onImageClickListener.onImageClick(position,binding.previewIv)
            }

        }
    }

    override fun getItemCount(): Int {
        return  mList.size
    }

    inner class ViewHolder(val binding: GalleryImageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}