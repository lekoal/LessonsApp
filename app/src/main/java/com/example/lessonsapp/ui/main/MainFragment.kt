package com.example.lessonsapp.ui.main

import android.os.Bundle
import android.view.View
import com.example.lessonsapp.data.LessonsListMock
import com.example.lessonsapp.databinding.FragmentMainBinding
import com.example.lessonsapp.utils.ViewBindingFragment

class MainFragment : ViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var classesAdapter: RVMainClassesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        classesAdapter = RVMainClassesAdapter()
        binding.rvClassesHome.adapter = classesAdapter
        val data = LessonsListMock()
        classesAdapter.setData(data.getLessons())
    }

    companion object {
        private const val EXAM_DATE = "10.11.2022"
    }
}