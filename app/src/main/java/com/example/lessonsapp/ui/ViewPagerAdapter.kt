package com.example.lessonsapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lessonsapp.ui.classes.ClassesFragment
import com.example.lessonsapp.ui.main.MainFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments = arrayOf(MainFragment(), ClassesFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragments[MAIN_FRAGMENT]
            1 -> fragments[CLASSES_FRAGMENT]
            else -> fragments[MAIN_FRAGMENT]
        }
    }

    companion object {
        private const val MAIN_FRAGMENT = 0
        private const val CLASSES_FRAGMENT = 1
    }

}