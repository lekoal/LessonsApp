package com.example.lessonsapp.di

import com.example.lessonsapp.ui.classes.ClassesViewModel
import com.example.lessonsapp.ui.classes.RVClassesAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val classesKoinModule = module {
    single(named("classes_adapter")) {
        RVClassesAdapter()
    }
    viewModel(named("classes_view_model")) {
        ClassesViewModel()
    }
}