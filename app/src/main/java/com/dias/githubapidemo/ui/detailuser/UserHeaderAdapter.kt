package com.dias.githubapidemo.ui.detailuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.databinding.ContentUserDetailBinding

class UserHeaderAdapter(private var user: User) :
    RecyclerView.Adapter<UserHeaderAdapter.UserHeaderViewHolder>() {

    private var _binding: ContentUserDetailBinding? = null
    private val binding get() = _binding as ContentUserDetailBinding

    class UserHeaderViewHolder(val binding: ContentUserDetailBinding, user: User) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.user = user
        }
    }

    fun setUser(user: User) {
        binding.user = user.copy(avatarUrl = null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : UserHeaderViewHolder {
        _binding = ContentUserDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHeaderViewHolder(
            binding,
            user
        )
    }

    override fun onBindViewHolder(holder: UserHeaderViewHolder, position: Int) {}

    override fun getItemCount() = 1
}