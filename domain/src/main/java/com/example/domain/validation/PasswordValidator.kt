package com.example.domain.validation

object PasswordValidator {
    private val regex =
        Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*]).{8,}$")
    fun validate(password: String): Boolean {
        return regex.matches(password)
    }
}