package com.dias.githubapidemo.views

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.dias.githubapidemo.R
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.databinding.ContentUserDetailBinding

class UserHeader(private val user: User) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val binding = ContentUserDetailBinding.inflate(LayoutInflater.from(parent.context))
        binding.user = user
        val bitmap = Bitmap.createBitmap(binding.root.width, binding.root.height, Bitmap.Config.ARGB_8888)
        c.setBitmap(bitmap)
        binding.root.draw(c)
    }
}