package com.example.lessonsapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonsapp.R
import com.example.lessonsapp.data.LessonsMockData
import java.text.SimpleDateFormat
import java.util.*

class RVMainClassesAdapter : RecyclerView.Adapter<RVMainClassesAdapter.MainViewHolder>() {
    private var data: List<LessonsMockData> = emptyList()

    var onItemClick: ((String) -> Unit)? = null

    fun setData(lessons: List<LessonsMockData>) {
        data = lessons
        notifyItemRangeInserted(0, lessons.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_classes_home_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): LessonsMockData = data[position]

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lessonLetter: AppCompatTextView = itemView.findViewById(R.id.lesson_letter_text)
        private val lessonTitle: AppCompatTextView = itemView.findViewById(R.id.lesson_title)
        private val lessonTimer: AppCompatTextView = itemView.findViewById(R.id.lesson_timer)
        private val openIn: FrameLayout = itemView.findViewById(R.id.open_in_skype)

        fun bind(item: LessonsMockData) {
            lessonTitle.text = item.name
            lessonTimer.text =
                itemView.context.getString(R.string.lesson_time_range, item.timeStart, item.timeEnd)
            lessonLetter.text = item.name.first().toString()
            val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val timeStart = formatter.parse(item.timeStart)
            val timeEnd = formatter.parse(item.timeEnd)
            val currentTime = formatter.parse(formatter.format(Date()))
            if (currentTime in timeStart..timeEnd) {
                openIn.visibility = View.VISIBLE
                openIn.setOnClickListener {
                    onItemClick?.invoke(item.name)
                }
            } else {
                openIn.visibility = View.GONE
            }
        }
    }
}