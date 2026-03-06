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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    val isButtonEnabled =
        combine(email, password) { email, pass ->

            EmailValidator.validate(email) &&
                    PasswordValidator.validate(pass)

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
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