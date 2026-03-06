package com.example.feature_login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.domain.validation.EmailValidator
import com.example.domain.validation.PasswordValidator
import com.example.feature_login.databinding.ActivityLoginBinding
import com.example.feature_login.viewmodel.LoginViewModel
import com.example.feature_result.ui.ResultActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupValidation()

        setupButton()

    }
    private fun setupButton() {

        binding.loginBtn.setOnClickListener {

            viewModel.register()

            startActivity(
                Intent(this, ResultActivity::class.java)
            )

        }

    }

    private fun setupValidation() {

        binding.emailInput.doAfterTextChanged {

            val email = it.toString()

            if (!EmailValidator.validate(email)) {

                binding.emailLayout.error =
                    "Некорректный email"

            } else {

                binding.emailLayout.error = null

                viewModel.email.value = email
            }
        }

        binding.passwordInput.doAfterTextChanged {

            val pass = it.toString()

            if (!PasswordValidator.validate(pass)) {

                binding.passwordLayout.error =
                    "Минимум 8 символов, 1 заглавная буква, цифра и спецсимвол"

            } else {

                binding.passwordLayout.error = null

                viewModel.password.value = pass
            }
        }

    }



}