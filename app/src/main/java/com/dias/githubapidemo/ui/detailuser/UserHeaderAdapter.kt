package com.dias.githubapidemo.ui.detailuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.databinding.ContentUserDetailBinding

class UserHeaderAdapter(private val user: User) :
    RecyclerView.Adapter<UserHeaderAdapter.UserHeaderViewHolder>() {
    class UserHeaderViewHolder(val binding: ContentUserDetailBinding, user: User) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.user = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserHeaderViewHolder(
        ContentUserDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        user
    )

    override fun onBindViewHolder(holder: UserHeaderViewHolder, position: Int) {}

    override fun getItemCount() = 1
}