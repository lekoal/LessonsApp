package com.example.lessonsapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonsapp.R
import com.example.lessonsapp.data.LessonsMockData
import java.text.SimpleDateFormat
import java.util.*

class RVMainHomeworksAdapter : RecyclerView.Adapter<RVMainHomeworksAdapter.MainViewHolder>() {
    private var data: List<LessonsMockData> = emptyList()

    fun setData(homeworks: List<LessonsMockData>) {
        data = homeworks
        notifyItemRangeInserted(0, homeworks.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_homeworks_home_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): LessonsMockData = data[position]

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val homeworkSubject: AppCompatTextView =
            itemView.findViewById(R.id.homework_subject)
        private val daysLeft: AppCompatTextView = itemView.findViewById(R.id.homework_days_left)
        private val homeworkLetter: AppCompatTextView = itemView.findViewById(R.id.homework_letter)
        private val homeworkText: AppCompatTextView = itemView.findViewById(R.id.homework_text)

        fun bind(item: LessonsMockData) {
            homeworkSubject.text = item.name
            homeworkLetter.text = item.name.first().toString()
            homeworkText.text = item.homeworkText

            val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
            val date = formatter.parse(item.dateStart)!!
            val fDate = date.time + (item.daysForHomework * 86400000)
            val fCurrentDay = formatter.parse(formatter.format(Date()))!!
            val milliseconds = fDate - fCurrentDay.time
            val daysBeforeHomework = milliseconds / 1000 / 3600 / 24

            daysLeft.text =
                itemView.context.getString(R.string.days_before_homework, daysBeforeHomework)
        }
    }
}