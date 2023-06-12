package com.udacity.shoestore.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class LoginViewModel: ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val isLoginEnabled: Flow<Boolean> = combine(_email, _password) { email, password ->
        val isValidEmail = email.length > 6 && email.contains("@")
        val isValidPassword = password.length > 8
        return@combine isValidEmail && isValidPassword
    }

    fun setEmailAddress(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}