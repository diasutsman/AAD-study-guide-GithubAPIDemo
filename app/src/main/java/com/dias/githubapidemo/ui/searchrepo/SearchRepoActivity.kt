package com.dias.githubapidemo.ui.searchrepo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.dias.githubapidemo.databinding.ActivitySearchRepoBinding
import com.dias.githubapidemo.ui.RepoAdapter

class SearchRepoActivity : AppCompatActivity() {

    private var _binding: ActivitySearchRepoBinding? = null
    private val binding get() = _binding as ActivitySearchRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this)[SearchRepoViewModel::class.java]
        val adapter = RepoAdapter()
        binding.apply {
            rvListRepo.adapter = adapter
            svSearchRepo.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.searchRepositories(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        viewModel.repoList.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}