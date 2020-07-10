package io.shivamvk.bareilly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import io.shivamvk.bareilly.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpBottomNav()
    }

    private fun loadHomeFragment() {
        binding.layoutBottomView.ivHomeIcon.setImageResource(R.drawable.ic_home_selected)
        binding.layoutBottomView.ivProfileIcon.setImageResource(R.drawable.ic_profile_unselected)
        binding.layoutBottomView.ivQueriesIcon.setImageResource(R.drawable.ic_queries_unselected)
    }

    private fun loadQueriesFragment() {
        binding.layoutBottomView.ivQueriesIcon.setImageResource(R.drawable.ic_queries_selected)
        binding.layoutBottomView.ivHomeIcon.setImageResource(R.drawable.ic_home_unselected)
        binding.layoutBottomView.ivProfileIcon.setImageResource(R.drawable.ic_profile_unselected)
    }

    private fun loadProfileFragment() {
        binding.layoutBottomView.ivProfileIcon.setImageResource(R.drawable.ic_profile_selected)
        binding.layoutBottomView.ivQueriesIcon.setImageResource(R.drawable.ic_queries_unselected)
        binding.layoutBottomView.ivHomeIcon.setImageResource(R.drawable.ic_home_unselected)
    }

    private fun setUpBottomNav() {
        loadHomeFragment()
        binding.layoutBottomView.llHome.setOnClickListener{ loadHomeFragment() }
        binding.layoutBottomView.llQueries.setOnClickListener{ loadQueriesFragment() }
        binding.layoutBottomView.llProfile.setOnClickListener { loadProfileFragment() }
    }
}