package com.sample.whosings.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sample.whosings.R
import com.sample.whosings.databinding.FragmentQuizFinishedBinding
import com.sample.whosings.ui.BaseActivity
import com.sample.whosings.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class QuizFinishedFragment : BaseFragment() {

    lateinit var binding: FragmentQuizFinishedBinding
    val viewModel : QuizFinishedViewModel by viewModel()
    var args: QuizFinishedFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizFinishedBinding.inflate(inflater, container, false)
        binding.retryQuiz.setOnClickListener {
            activity?.let {act ->
                if(act is BaseActivity){
                    showLoader(act)
                }
            }
            val action = QuizFinishedFragmentDirections.actionToQuizFragment()
            findNavController().navigate(action)
        }
        binding.goHome.setOnClickListener {
            val action = QuizFinishedFragmentDirections.actionToHomeFragment()
            findNavController().navigate(action)
        }
        args = arguments?.let { QuizFinishedFragmentArgs.fromBundle(it) }
        val score = "${args?.score ?: 0}"
        binding.title.text = getString(R.string.score_description, score)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args?.score?.let {
            viewModel.updateUserScore(it)
        }
    }
}