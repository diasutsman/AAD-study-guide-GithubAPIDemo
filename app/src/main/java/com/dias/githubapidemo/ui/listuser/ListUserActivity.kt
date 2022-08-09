package com.dias.githubapidemo.ui.listuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dias.githubapidemo.databinding.ActivityListUserBinding
import com.dias.githubapidemo.ui.UserAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListUserActivity : AppCompatActivity() {

    // the usual bindings
    private var _binding: ActivityListUserBinding? = null
    private val binding get() = _binding as ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListUserBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[ListUserViewModel::class.java]
        val adapter = UserAdapter()
        binding.rvListUser.adapter = adapter
        lifecycleScope.launch {
            viewModel.listUser.collectLatest(adapter::submitData)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}