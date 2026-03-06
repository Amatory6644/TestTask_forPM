package com.example.feature_login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.feature_login.databinding.ActivityLoginBinding
import com.example.feature_login.viewmodel.LoginViewModel
import com.example.feature_result.ui.ResultActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {

        binding.emailInput.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }

        binding.passwordInput.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }

        binding.loginBtn.setOnClickListener {

            viewModel.register()

            startActivity(
                Intent(this, ResultActivity::class.java)
            )
        }

        binding.googleButton.setOnClickListener {
            Toast.makeText(this, "Google login", Toast.LENGTH_SHORT).show()
        }

        binding.vkButton.setOnClickListener {
            Toast.makeText(this, "VK login", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {

        lifecycleScope.launch {

            viewModel.emailError.collect {
                binding.emailLayout.error = it

            }

        }

        lifecycleScope.launch {
            viewModel.isEmailValid.collect { isValid ->

                binding.emailLayout.isEndIconVisible =
                    isValid && binding.emailInput.text?.isNotEmpty() == true

            }
        }

        lifecycleScope.launch {

            viewModel.passwordError.collect {
                binding.passwordLayout.error = it
            }

        }

        lifecycleScope.launch {

            viewModel.isButtonEnabled.collect {

                binding.loginBtn.apply {
                    isEnabled = it
                    alpha = if (it) 1f else 0.5f
                }

            }

        }

    }
}