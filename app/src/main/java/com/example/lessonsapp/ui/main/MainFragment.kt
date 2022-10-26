package com.example.lessonsapp.ui.main

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.lessonsapp.R
import com.example.lessonsapp.databinding.FragmentMainBinding
import com.example.lessonsapp.utils.ViewBindingFragment
import com.google.android.material.chip.Chip
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : ViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val classesAdapter: RVMainClassesAdapter by inject(named("classes_adapter"))
    private val homeworksAdapter: RVMainHomeworksAdapter by inject(named("homeworks_adapter"))
    private val viewModel: MainViewModel by viewModel(named("main_view_model"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        sendSkypeIntent()
        setData()
        viewModel.getData()
    }

    companion object {
        private const val EXAM_DATE = "08.11.2022, 10:00"
    }

    private fun initRV() {
        binding.rvClassesHome.adapter = classesAdapter
        binding.rvHomeworks.adapter = homeworksAdapter
    }

    private fun sendSkypeIntent() {
        classesAdapter.onItemClick = { lessonName ->
            try {
                val skypeUri = Uri.parse(lessonName)
                val intent = Intent(Intent.ACTION_VIEW, skypeUri)
                intent.component = ComponentName("com.skype.raider", "com.skype.raider.Main")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                requireContext().startActivity(intent)
                Toast.makeText(requireContext(), "Open Skype", Toast.LENGTH_SHORT).show()
            } catch (e: ActivityNotFoundException) {
                Log.e("error", e.message.toString())
                val marketUri = Uri.parse("market://details?id=com.skype.raider")
                val skypeMarketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                skypeMarketIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                requireContext().startActivity(skypeMarketIntent)
            }
        }
    }

    private fun setData() {
        viewModel.data.observe(viewLifecycleOwner) { list ->
            classesAdapter.setData(list)
            homeworksAdapter.setData(list)
        }
        binding.mainClassesToday.text = getString(R.string.classes_today, classesAdapter.itemCount)

        val timeDiff = getDateDiff(EXAM_DATE)
        val day = timeDiff[0].toCharArray()
        val hour = timeDiff[1].toCharArray()
        val minute = timeDiff[2].toCharArray()

        bindDateToExam(day, binding.daysFirst, binding.daysSecond)
        bindDateToExam(hour, binding.hoursFirst, binding.hoursSecond)
        bindDateToExam(minute, binding.minutesFirst, binding.minutesSecond)
    }

    private fun getDateDiff(examDay: String, fmt: String = "dd.MM.yyyy, HH:mm"): List<String> {
        val formatter = SimpleDateFormat(fmt, Locale.ENGLISH)
        val fCurrentDay = formatter.parse(formatter.format(Date()))!!
        val fExamDay = formatter.parse(examDay)!!

        val milliseconds = fExamDay.time - fCurrentDay.time
        val days = (milliseconds / 1000 / 3600 / 24)
        val hours = (milliseconds - (days * 86400000)) / 1000 / 3600
        val minutes = ((milliseconds - (hours * 3600000)) / 1000 / 36000)

        return listOf(days.toString(), hours.toString(), minutes.toString())
    }

    private fun bindDateToExam(
        array: CharArray,
        viewFirst: Chip,
        viewSecond: Chip
    ) {
        if (array.size == 1) {
            viewFirst.text = "0"
            viewSecond.text = array[0].toString()
        } else {
            viewFirst.text = array[0].toString()
            viewSecond.text = array[1].toString()
        }
    }
}