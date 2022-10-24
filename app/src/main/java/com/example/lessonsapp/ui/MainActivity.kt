package com.example.lessonsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.lessonsapp.R
import com.example.lessonsapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { _, _ -> }.attach()
        setCustomTabs()
        activeTabListener()
    }

    companion object {
        private const val HOME = 0
        private const val CLASSES = 1
    }

    private fun setCustomTabs() {
        binding.tabLayout.getTabAt(HOME)?.setCustomView(R.layout.view_pager_tab_home_selected)
        binding.tabLayout.getTabAt(CLASSES)?.setCustomView(R.layout.view_pager_tab_classes)
    }

    private fun activeTabListener() {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setHighLightedTab(position)
            }
        })
    }

    private fun setHighLightedTab(position: Int) {
        when (position) {
            HOME -> {
                binding.tabLayout.getTabAt(HOME)?.customView = null
                binding.tabLayout.getTabAt(HOME)
                    ?.setCustomView(R.layout.view_pager_tab_home_selected)
                binding.tabLayout.getTabAt(CLASSES)?.customView = null
                binding.tabLayout.getTabAt(CLASSES)?.setCustomView(R.layout.view_pager_tab_classes)
            }
            CLASSES -> {
                binding.tabLayout.getTabAt(HOME)?.customView = null
                binding.tabLayout.getTabAt(HOME)?.setCustomView(R.layout.view_pager_tab_home)
                binding.tabLayout.getTabAt(CLASSES)?.customView = null
                binding.tabLayout.getTabAt(CLASSES)
                    ?.setCustomView(R.layout.view_pager_tab_classes_selected)
            }
        }
    }
}