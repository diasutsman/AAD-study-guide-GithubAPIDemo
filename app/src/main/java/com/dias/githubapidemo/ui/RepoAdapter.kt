package com.dias.githubapidemo.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dias.githubapidemo.R
import com.dias.githubapidemo.data.Repo
import com.dias.githubapidemo.databinding.RowItemRepoBinding

class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.RepoViewHolder>(DIFF_CALLBACK) {
    class RepoViewHolder(val binding: RowItemRepoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepoViewHolder(
            RowItemRepoBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.binding.apply {
            val context = holder.itemView.context
            repo = getItem(position)
            repo?.language?.let {
                repoLanguage.text = context.getString(R.string.language, repo?.language)
            }
            repoName.setOnClickListener {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(repo?.htmlUrl))
                )
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}