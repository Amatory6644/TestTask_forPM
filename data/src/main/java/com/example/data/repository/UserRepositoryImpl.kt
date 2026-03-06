package com.example.data.repository

import com.example.data.local.UserDao
import com.example.data.local.UserEntity
import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class UserRepositoryImpl(

    private val dao: UserDao

) : UserRepository {

    override suspend fun saveUser(user: User) {

        dao.insert(

            UserEntity(

                email = user.email,

                password = user.password

            )

        )

    }

    override suspend fun getLastUser(): User? {

        return dao.getLastUser()?.let {

            User(

                it.email,

                it.password

            )

        }

    }

}