package com.dias.githubapidemo.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.databinding.RowItemUserBinding
import com.dias.githubapidemo.ui.detailuser.UserDetailActivity

class UserAdapter : PagingDataAdapter<User, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {
    class UserViewHolder(private val binding: RowItemUserBinding, val onClickListener: (User) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClickListener(binding.user as User)
            }
        }
        fun bind(user: User?) {
            binding.user = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(RowItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        ){
            val context = parent.context
            context.startActivity(Intent(context, UserDetailActivity::class.java).putExtra(
                UserDetailActivity.USER_KEY, it))
        }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
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