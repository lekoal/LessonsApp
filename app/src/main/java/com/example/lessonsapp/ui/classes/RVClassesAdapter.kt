package com.example.lessonsapp.ui.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonsapp.R
import com.example.lessonsapp.data.LessonsMockData
import java.text.SimpleDateFormat
import java.util.*

class RVClassesAdapter : RecyclerView.Adapter<RVClassesAdapter.ClassesViewHolder>() {
    private var data: List<LessonsMockData> = emptyList()

    var onItemClick: ((String) -> Unit)? = null

    fun setData(list: List<LessonsMockData>) {
        data = list
        notifyItemRangeInserted(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_classes_classes_item, parent, false)
        return ClassesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        if (position == itemCount - 1) {
            holder.bind(getItem(position), true)
        } else {
            holder.bind(getItem(position), false)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): LessonsMockData = data[position]

    inner class ClassesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val selectedDot: CardView = itemView.findViewById(R.id.rv_selected_dot)
        private val rvLine: View = itemView.findViewById(R.id.rv_line)
        private val rvClassTime: AppCompatTextView = itemView.findViewById(R.id.rv_class_time)
        private val lessonLetter: AppCompatTextView =
            itemView.findViewById(R.id.rv_lesson_letter_text)
        private val rvLessonTitle: AppCompatTextView = itemView.findViewById(R.id.rv_lesson_title)
        private val rvLessonTeacher: AppCompatTextView =
            itemView.findViewById(R.id.rv_lesson_teacher)
        private val openIn: FrameLayout = itemView.findViewById(R.id.open_in_skype)

        fun bind(item: LessonsMockData, isLast: Boolean) {
            if (isLast) {
                rvLine.visibility = View.INVISIBLE
            }
            val lessonTimer =
                itemView.context.getString(R.string.lesson_time_range, item.timeStart, item.timeEnd)
            rvClassTime.text = lessonTimer
            rvLessonTitle.text = item.name
            rvLessonTeacher.text =
                itemView.context.getString(R.string.teacher_name, item.teacher)
            lessonLetter.text = item.name.first().toString()

            val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val timeStart = formatter.parse(item.timeStart)
            val timeEnd = formatter.parse(item.timeEnd)
            val currentTime = formatter.parse(formatter.format(Date()))
            if (currentTime in timeStart..timeEnd) {
                selectedDot.visibility = View.VISIBLE
                openIn.visibility = View.VISIBLE
                openIn.setOnClickListener {
                    onItemClick?.invoke(item.name)
                }
            } else {
                selectedDot.visibility = View.INVISIBLE
                openIn.visibility = View.GONE
            }
        }

    }
}