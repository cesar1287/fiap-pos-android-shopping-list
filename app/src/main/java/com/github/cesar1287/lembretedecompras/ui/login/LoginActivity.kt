package com.github.cesar1287.lembretedecompras.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.github.cesar1287.lembretedecompras.R
import com.github.cesar1287.lembretedecompras.databinding.ActivityLoginBinding
import com.github.cesar1287.lembretedecompras.model.RequestState
import com.github.cesar1287.lembretedecompras.model.User
import com.github.cesar1287.lembretedecompras.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var animacaoDoMascote: Animation
    private lateinit var animacaoDoFormulario: Animation

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAnimation()

        hideKeyboard()

        initListener()

        initObserver()
    }

    private fun initListener() {
        binding.btLogin.setOnClickListener {
            loginViewModel.login(
                User(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
        }

        binding.etPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.ivLogin.speed = 2f
                binding.ivLogin.setMinAndMaxProgress(0.0f, 0.65f)
            } else {
                binding.ivLogin.speed = 1f
                binding.ivLogin.setMinAndMaxProgress(0.65f, 1.0f)
            }
            binding.ivLogin.playAnimation()
        }

    }

    private fun initObserver() {
        loginViewModel.loginState.observe(this) {
            when (it) {
                is RequestState.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is RequestState.Error -> {
                    val animShake = AnimationUtils.loadAnimation(this, R.anim.shake)
                    binding.containerLogin.startAnimation(animShake)
                    binding.tvPasswordFeedback.text = it.throwable.message
                }
                is RequestState.Loading -> {

                }
            }
        }
    }


    private fun initAnimation() {
        with(binding) {
            animacaoDoMascote = AnimationUtils.loadAnimation(this@LoginActivity, R.anim.frombottom2)
            animacaoDoFormulario = AnimationUtils.loadAnimation(
                this@LoginActivity,
                R.anim.frombottom
            )
            ivLogin.clearAnimation()
            containerLogin.clearAnimation()
            containerLogin.startAnimation(animacaoDoFormulario)
            ivLogin.startAnimation(animacaoDoMascote)
        }
    }

    private fun hideKeyboard() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

}