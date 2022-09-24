package com.example.nasagallery.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.nasagallery.R
import com.example.nasagallery.databinding.ImageDetailsItemBinding
import com.example.nasagallery.models.ImagesDataListItem
import com.example.nasagallery.utils.OnImageLoadListener


class ImageDetailAdapter(
    private val mList: ArrayList<ImagesDataListItem?>?,
    private val context: Context,
    private val imageLoadListener: OnImageLoadListener
) : RecyclerView.Adapter<ImageDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ImageDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            mList?.get(position)?.let {
                val options: RequestOptions = RequestOptions()
                    .centerInside()
                    .placeholder(R.drawable.ic_img_place_holder)
                    .error(R.drawable.ic_img_place_holder)

                Glide.with(context)
                    .load(it.url)
                    .apply(options)
                    .dontAnimate()
                    .listener(object :RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageLoadListener.onImageLoadedFail()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageLoadListener.onImageLoadedSuccess()
                            return false
                        }

                    }).into(binding.detailedIv)
                binding.titleTv.text=it.title
                binding.descTv.text=it.explanation
                binding.takenOnTv.text="Taken On : ${it.date}"
            }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    inner class ViewHolder(val binding: ImageDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}