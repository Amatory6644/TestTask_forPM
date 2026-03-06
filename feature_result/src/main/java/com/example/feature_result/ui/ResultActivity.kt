package com.example.feature_result.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.feature_result.R
import com.example.feature_result.databinding.ActivityResultBinding
import com.example.feature_result.viewmodel.ResultViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val viewModel: ResultViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel.loadUser()

        lifecycleScope.launch {

            viewModel.user.collect {

                if (it == null) {

                    binding.emailText.text =
                        getString(R.string.nosaveddata)

                } else {

                    binding.emailText.text =
                        getString(R.string.email, it.email)

                    binding.passwordText.text =
                        getString(R.string.password, it.password)

                }

            }

        }

    }

}