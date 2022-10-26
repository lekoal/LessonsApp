package com.example.lessonsapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lessonsapp.data.LessonsListMock
import com.example.lessonsapp.data.LessonsMockData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<List<LessonsMockData>>()
    val data: LiveData<List<LessonsMockData>> = _data

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getData() {
        coroutineScope.launch {
            val lessons = LessonsListMock()
            _data.postValue(lessons.getLessons())
        }
    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}