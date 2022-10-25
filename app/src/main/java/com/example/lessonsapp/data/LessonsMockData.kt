package com.example.lessonsapp.data

data class LessonsMockData(
    val name: String,
    val dateStart: String,
    val timeStart: String,
    val timeEnd: String,
    val teacher: String,
    val homeworkText: String,
    val daysForHomework: Int = 5
)
