package com.example.lessonsapp.di

import com.example.lessonsapp.ui.main.MainViewModel
import com.example.lessonsapp.ui.main.RVMainClassesAdapter
import com.example.lessonsapp.ui.main.RVMainHomeworksAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainKoinModule = module {
    single(named("classes_adapter")) {
        RVMainClassesAdapter()
    }
    single(named("homeworks_adapter")) {
        RVMainHomeworksAdapter()
    }
    viewModel(named("main_view_model")) {
        MainViewModel()
    }
}