package com.dias.githubapidemo.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}