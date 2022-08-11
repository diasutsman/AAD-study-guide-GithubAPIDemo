package com.dias.githubapidemo.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dias.githubapidemo.R
import com.dias.githubapidemo.data.Repo
import com.dias.githubapidemo.databinding.RowItemRepoBinding

class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.RepoViewHolder>(DIFF_CALLBACK) {
    class RepoViewHolder(
        private val binding: RowItemRepoBinding,
        private val onClickListener: (Repo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClickListener(binding.repo as Repo)
            }
        }

        fun bind(mRepo: Repo?) {
            binding.repo = mRepo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepoViewHolder(
            RowItemRepoBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        ) { repo ->
            val context = parent.context
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl))
            )
        }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
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