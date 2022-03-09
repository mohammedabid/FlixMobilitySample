package com.example.timetablesample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.timetablesample.databinding.FragmentTimeTableBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TimeTableFragment : Fragment() {

    private val timeTableAdapter = TimeTableAdapter()


    private val viewModel: TimeTableViewModel by viewModels()


    private var _binding: FragmentTimeTableBinding? = null

    val binding: FragmentTimeTableBinding
    get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvTimeTable.apply {
            adapter = timeTableAdapter
        }

        binding.srlShapes.setOnRefreshListener {
            binding.srlShapes.isRefreshing = false
            viewModel.getTimeTable()
        }

        viewModel.getTimeTable()

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.timeTableList.collect {

                if (it.isLoading) {
//                    binding.nothingFound.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
//                    binding.nothingFound.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {list ->

                    if (list.isEmpty()) {
//                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progress.visibility = View.GONE
                    timeTableAdapter.setContentList(list.toMutableList())
                }

            }
        }


    }

}