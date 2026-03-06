package com.example.domain.validation

import android.util.Patterns

object EmailValidator {

    fun validate(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}