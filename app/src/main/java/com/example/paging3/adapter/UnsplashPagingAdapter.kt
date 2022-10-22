package com.example.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.paging3.R
import com.example.paging3.databinding.ItemBinding
import com.example.paging3.model.UnsplashImage

class UnsplashPagingAdapter :
    PagingDataAdapter<UnsplashImage, UnsplashPagingAdapter.VH>(COMPARATOR) {

    class VH(private val itemRV: ItemBinding) : RecyclerView.ViewHolder(itemRV.root) {
        fun onBind(unsplashImage: UnsplashImage) {
            itemRV.tvName.text = unsplashImage.user.username
            itemRV.tvLikeCount.text = unsplashImage.likes.toString()
            itemRV.image.load(unsplashImage.urls.regular) {
                crossfade(durationMillis = 1000)
                error(R.drawable.ic_placeholder)
                placeholder(R.drawable.ic_placeholder)
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.onBind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UnsplashImage>() {
            override fun areItemsTheSame(oldItem: UnsplashImage, newItem: UnsplashImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashImage,
                newItem: UnsplashImage
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}