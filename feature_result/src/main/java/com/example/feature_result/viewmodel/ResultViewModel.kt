package com.example.feature_result.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.User
import com.example.domain.usecase.GetLastUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResultViewModel(

    private val getLastUserUseCase: GetLastUserUseCase

) : ViewModel() {

    val user = MutableStateFlow<User?>(null)

    fun loadUser() {

        viewModelScope.launch {

            user.value = getLastUserUseCase()

        }

    }

}