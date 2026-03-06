package com.example.domain.repository

import com.example.domain.model.User

interface UserRepository {

    suspend fun saveUser(user: User)

    suspend fun getLastUser(): User?

}