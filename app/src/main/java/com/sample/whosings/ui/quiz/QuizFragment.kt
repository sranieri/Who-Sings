package com.sample.whosings.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sample.whosings.R
import com.sample.whosings.databinding.FragmentQuizBinding
import com.sample.whosings.ui.BaseActivity
import com.sample.whosings.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class QuizFragment : BaseFragment(), View.OnClickListener {

    private val viewModel: QuizViewModel by viewModel()

    lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.firstAnswer.setOnClickListener(this)
        binding.secondAnswer.setOnClickListener(this)
        binding.thirdAnswer.setOnClickListener(this)

        setupObservers()
        viewModel.fetchTracks()
        return binding.root
    }

    fun setupObservers(){
        viewModel.trackList.observe(viewLifecycleOwner, {
            viewModel.startQuiz()
        })

        viewModel.currentQuestion.observe(viewLifecycleOwner, { (question, index, totalQuestions) ->
            val s = "$index/$totalQuestions"
            binding.questionIndicator.text = s
            activity?.let { act ->
                if (act is BaseActivity) {
                    hideLoader(act)
                }
            }
            if (question.trackId != 0) {
                viewModel.getLyrics(question.trackId.toString())
            }
            binding.thirdAnswer.reset()
            binding.firstAnswer.reset()
            binding.secondAnswer.reset()
            binding.firstAnswer.text = question.firstAnswer
            binding.secondAnswer.text = question.secondAnswer
            binding.thirdAnswer.text = question.thirdAnswer
        })

        viewModel.lyrics.observe(viewLifecycleOwner, {
            binding.question.text = it
        })

        viewModel.currentResult.observe(viewLifecycleOwner, {
            when (it.answerText) {
                binding.firstAnswer.text.toString() -> {
                    binding.firstAnswer.onSuccess()
                }
                binding.secondAnswer.text.toString() -> {
                    binding.secondAnswer.onSuccess()
                }
                binding.thirdAnswer.text.toString() -> {
                    binding.thirdAnswer.onSuccess()
                }
            }
            if (!it.isSuccess) {
                when (it.wrongAnswerText) {
                    binding.firstAnswer.text.toString() -> {
                        binding.firstAnswer.onError()
                    }
                    binding.secondAnswer.text.toString() -> {
                        binding.secondAnswer.onError()
                    }
                    binding.thirdAnswer.text.toString() -> {
                        binding.thirdAnswer.onError()
                    }
                }
            }
            binding.root.animate()
                .alpha(0f)
                .setDuration(1000)
                .start()
            viewModel.goToNextQuestion()
            binding.root.animate()
                .alpha(1f)
                .setDuration(1000)
                .setStartDelay(1000)
                .start()
        })

        viewModel.quizResult.observe(viewLifecycleOwner, {
            val action = QuizFragmentDirections.actionToQuizFinishedFragment(it)
            findNavController().navigate(action)
        })

        viewModel.countDown.observe(viewLifecycleOwner){
            binding.timer.progress = (it * 100f)/30f
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.firstAnswer -> {
                checkAnswer(binding.firstAnswer.text.toString())
            }
            R.id.secondAnswer -> {
                checkAnswer(binding.secondAnswer.text.toString())
            }
            R.id.thirdAnswer -> {
                checkAnswer(binding.thirdAnswer.text.toString())
            }
        }
    }

    private fun checkAnswer(answer: String){
        viewModel.checkAnswer(answer)
    }
}