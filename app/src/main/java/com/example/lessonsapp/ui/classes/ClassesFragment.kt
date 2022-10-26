package com.example.lessonsapp.ui.classes

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.lessonsapp.databinding.FragmentClassesBinding
import com.example.lessonsapp.utils.ViewBindingFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ClassesFragment :
    ViewBindingFragment<FragmentClassesBinding>(FragmentClassesBinding::inflate) {
    private val adapter: RVClassesAdapter by inject(named("classes_adapter"))
    private val viewModel: ClassesViewModel by viewModel(named("classes_view_model"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
        rvInit()
        setData()
        openSkype()
    }

    private fun rvInit() {
        binding.rvClassesClasses.adapter = adapter
    }

    private fun setData() {
        viewModel.data.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }
    }

    private fun openSkype() {
        adapter.onItemClick = { lessonName ->
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

}