package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class GetLastUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): User? {
        return repository.getLastUser()
    }
}