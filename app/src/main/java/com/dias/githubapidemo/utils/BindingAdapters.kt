package com.dias.githubapidemo.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dias.githubapidemo.data.User

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("formatBio")
    fun formatBio(tvBio: TextView, user: User) {
        tvBio.text =
            listOf(
                user.company,
                user.location,
                user.email,
                user.blog,
                user.twitterUsername,
                user.bio,
            ).filter { str -> !str.isNullOrEmpty() }.joinToString("\n")
    }
}