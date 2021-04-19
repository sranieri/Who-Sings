package com.sample.whosings.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.sample.whosings.databinding.FragmentLoginBinding
import com.sample.whosings.ui.BaseFragment
import com.sample.whosings.utils.getSingleLineText
import com.sample.whosings.utils.toSingleLineString
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    lateinit var binding: FragmentLoginBinding

    val viewModel : LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel.loginLiveData.observe(viewLifecycleOwner, {
            val action = LoginFragmentDirections.actionFromLoginToHome()
            findNavController().navigate(action)
        })

        binding.loginButton.setOnClickListener {
            val username = binding.username.getSingleLineText()
            if(username.isNotEmpty()) {
                viewModel.loginUser(username)
            }
        }

        binding.username.doOnTextChanged { text, _, _, _ ->
            binding.loginButton.isEnabled = text?.toSingleLineString()?.isNotEmpty() == true
        }

        return binding.root
    }
}