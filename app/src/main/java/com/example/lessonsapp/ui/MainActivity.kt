package com.example.lessonsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.lessonsapp.R
import com.example.lessonsapp.databinding.ActivityMainBinding
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
        activeTabListener()
    }

    companion object {
        private const val HOME = 0
        private const val CLASSES = 1
    }

    private fun activeTabListener() {

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setHighLightedTab(position)
                super.onPageSelected(position)
            }
        })
        binding.tabLayout.getTabAt(CLASSES)?.select()
        binding.tabLayout.getTabAt(HOME)?.select()
    }

    private fun setHighLightedTab(position: Int) {
        when (position) {
            HOME -> {
                activateTab(HOME)
                deactivateTab(CLASSES)
            }
            CLASSES -> {
                activateTab(CLASSES)
                deactivateTab(HOME)
            }
        }
    }

    private fun activateTab(tabIndex: Int) {
        binding.tabLayout.getTabAt(tabIndex)?.customView = null
        when (tabIndex) {
            HOME -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_tab_home_selected)
            CLASSES -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_tab_classes_selected)
        }
    }

    private fun deactivateTab(tabIndex: Int) {
        binding.tabLayout.getTabAt(tabIndex)?.customView = null
        when (tabIndex) {
            HOME -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_tab_home)
            CLASSES -> binding.tabLayout.getTabAt(tabIndex)
                ?.setCustomView(R.layout.view_pager_tab_classes)
        }
    }

}