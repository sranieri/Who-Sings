package com.sample.whosings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sample.whosings.R
import com.sample.whosings.databinding.FragmentHomeBinding
import com.sample.whosings.ui.BaseActivity
import com.sample.whosings.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(){

    lateinit var binding: FragmentHomeBinding

    val viewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.startQuiz.setOnClickListener {
            activity?.let {
                if(it is BaseActivity){
                    showLoader(it)
                }
            }
            val action = HomeFragmentDirections.actionToQuizFragment()
            findNavController().navigate(action)
        }

        binding.logoutButton.setOnClickListener {
            viewModel.logoutUser()
        }

        binding.chart.setOnClickListener {
            val action = HomeFragmentDirections.actionToChartFragment()
            findNavController().navigate(action)
        }

        viewModel.currentUser.observe(viewLifecycleOwner, {
            if(it == null){
                val action = HomeFragmentDirections.actionFromHomeToLogin()
                findNavController().navigate(action)
            } else {
                binding.username.text = it.username
                val score = "${it.score}"
                binding.score.text = getString(R.string.score_description, score)
                viewModel.fetchTracks()
            }
        })

        viewModel.logoutLiveData.observe(viewLifecycleOwner, {
            if(it){
                val action = HomeFragmentDirections.actionFromHomeToLogin()
                findNavController().navigate(action)
            }
        })

        viewModel.trackList.observe(viewLifecycleOwner, {
            if(it.isNotEmpty()){
                binding.startQuiz.isEnabled = true
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentUser()
    }
}