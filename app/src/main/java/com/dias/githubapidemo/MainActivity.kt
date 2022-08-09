package com.dias.githubapidemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dias.githubapidemo.databinding.ActivityMainBinding
import com.dias.githubapidemo.ui.listuser.ListUserActivity
import com.dias.githubapidemo.ui.searchrepo.SearchRepoActivity
import com.dias.githubapidemo.ui.searchuser.SearchUserActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnRvListUser.setOnClickListener(this@MainActivity)
            btnRvSearchRepo.setOnClickListener(this@MainActivity)
            btnRvSearchUser.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.btn_rv_list_user -> {
                    startActivity(Intent(this, ListUserActivity::class.java))
                }
                R.id.btn_rv_search_repo -> {
                    startActivity(Intent(this, SearchRepoActivity::class.java))
                }
                R.id.btn_rv_search_user -> {
                    startActivity(Intent(this, SearchUserActivity::class.java))
                }
            }
        }
    }
}