package com.dias.githubapidemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dias.githubapidemo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navView: BottomNavigationView = binding.bottomNavigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_activity_main) as NavHostFragment
        navView.setupWithNavController(navHostFragment.navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.action_list,
                R.id.action_search_repo,
                R.id.action_search_user
            )
        )
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

}