package com.dias.githubapidemo.ui

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.databinding.RowItemUserBinding
import com.dias.githubapidemo.ui.detailuser.UserDetailActivity

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {
    class UserViewHolder(val binding: RowItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(RowItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.apply {
            this.user = user
            val context = root.context
            root.setOnClickListener {
                context.startActivity(Intent(context, UserDetailActivity::class.java).putExtra(
                    UserDetailActivity.USER_NAME, user.login))
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

}