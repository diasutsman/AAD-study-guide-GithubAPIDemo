package com.dias.githubapidemo.ui.detailuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.databinding.ActivityDetailUserBinding
import com.dias.githubapidemo.ui.RepoAdapter
import kotlinx.coroutines.launch

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
        val mUser = intent.extras?.getParcelable<User>(USER_KEY) as User
        val mUserHeaderAdapter = UserHeaderAdapter(mUser)
        title = mUser.login

        binding.apply {
            rvRepos.adapter = ConcatAdapter(mUserHeaderAdapter, mAdapter)
        }

        viewModel.apply {
            getUserDetail(mUser.login.toString())
            getUserRepos(mUser.login.toString())
            user.observe(this@UserDetailActivity) {
                mUserHeaderAdapter.setUser(it)
            }

            repos.observe(this@UserDetailActivity) {
                lifecycleScope.launch {
                    mAdapter.submitData(it)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val USER_KEY = "user_key"
    }
}