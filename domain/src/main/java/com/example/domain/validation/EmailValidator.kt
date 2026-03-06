package com.example.domain.validation

object EmailValidator {

    fun validate(email: String): Boolean {

        if (email.contains(" ")) return false

        if (!email.contains("@")) return false

        if (!email.contains(".")) return false

        return true
    }
}