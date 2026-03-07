package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)
    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    suspend fun getLastUser(): UserEntity?
}