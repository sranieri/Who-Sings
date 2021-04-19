package com.sample.whosings.ui.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.whosings.databinding.FragmentChartBinding
import com.sample.whosings.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class ChartFragment : BaseFragment() {

    lateinit var binding: FragmentChartBinding
    val viewModel : ChartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChartBinding.inflate(inflater, container, false)

        binding.goBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.chartLiveData.observe(viewLifecycleOwner, {
            val recyclerViewAdapter = ChartAdapter(requireContext(), it)
            binding.chart.layoutManager = LinearLayoutManager(context)
            binding.chart.adapter = recyclerViewAdapter
        })

        viewModel.getChart()
        return binding.root
    }
}