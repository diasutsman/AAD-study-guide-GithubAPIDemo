package com.dias.githubapidemo.ui.detailuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dias.githubapidemo.databinding.ActivityDetailUserBinding
import com.dias.githubapidemo.ui.RepoAdapter
import com.dias.githubapidemo.ui.UserAdapter
import com.dias.githubapidemo.views.UserHeader

class UserDetailActivity : AppCompatActivity() {

    // usual bindings
    private var _binding: ActivityDetailUserBinding? = null
    private val binding get() = _binding as ActivityDetailUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this)[UserDetailViewModel::class.java]
        val mAdapter = RepoAdapter()
        binding.rvRepos.apply {
            adapter = mAdapter
        }

        viewModel.apply {
            val username = intent.getStringExtra(USER_NAME).toString()
            getUserDetail(username)
            getUserRepos(username)
            user.observe(this@UserDetailActivity) {
                binding.rvRepos.addItemDecoration(UserHeader(it))
//                binding.apply {
//                    user = it
//                    tvBio.text =
//                        listOf(
//                            it.company,
//                            it.location,
//                            it.email,
//                            it.blog,
//                            it.twitterUsername,
//                            it.bio,
//                        ).filter { str -> !str.isNullOrEmpty() }.joinToString("\n")
//                }
                title = it.login
            }

            repos.observe(this@UserDetailActivity) {
                mAdapter.submitList(it)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val USER_NAME = "USER_NAME"
    }
}