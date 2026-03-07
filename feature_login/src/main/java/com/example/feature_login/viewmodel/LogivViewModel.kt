package com.example.feature_login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.User
import com.example.domain.usecase.SaveUserUseCase
import com.example.domain.validation.EmailValidator
import com.example.domain.validation.PasswordValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError = _emailError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError = _passwordError

    fun onEmailChanged(value: String) {
        email.value = value
        _emailError.value =
            when {
                value.isBlank() -> null
                EmailValidator.validate(value) -> null
                else ->"Проверьте правильность написания почты"
            }
    }

    fun onPasswordChanged(value: String) {
        password.value = value
        _passwordError.value =
            when {
                value.isBlank() -> null
                PasswordValidator.validate(value) -> null
                else -> "Проверьте правильность написания пароля"
            }
    }

    val isButtonEnabled =
        combine(email, password) { email, pass ->
            EmailValidator.validate(email) &&
                    PasswordValidator.validate(pass)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    val isEmailValid =
        email
            .map {
                EmailValidator.validate(it)
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                false
            )

    fun register() {
        viewModelScope.launch {
            saveUserUseCase(
                User(
                    email.value,
                    password.value
                )
            )
        }
    }
}