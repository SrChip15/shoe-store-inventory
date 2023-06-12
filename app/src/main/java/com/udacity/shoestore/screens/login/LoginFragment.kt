package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        initListeners()
        initObserver()

        return binding.root
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoginEnabled.collect { value ->
                binding.loginButton.isEnabled = value
            }
        }
    }

    private fun initListeners() {
        binding.emailField.doOnTextChanged { text, _, _, _ ->
            viewModel.setEmailAddress(text.toString())
        }
        binding.passwordField.doOnTextChanged { text, _, _, _ ->
            viewModel.setPassword(text.toString())

        }

        binding.loginButton.setOnClickListener {
            clearFields()
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            )
        }
        binding.signupButton.setOnClickListener {
            clearFields()
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            )
        }
    }

    private fun clearFields() {
        binding.emailField.text?.clear()
        binding.passwordField.text?.clear()
    }
}