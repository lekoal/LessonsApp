package com.example.lessonsapp.ui.classes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lessonsapp.R
import com.example.lessonsapp.databinding.FragmentClassesBinding

class ClassesFragment : Fragment() {

    private var _binding: FragmentClassesBinding? = null
    private val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = ClassesFragment()

    }
}