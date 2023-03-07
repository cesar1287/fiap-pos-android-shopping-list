package com.github.cesar1287.lembretedecompras.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.github.cesar1287.lembretedecompras.R
import com.github.cesar1287.lembretedecompras.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var animacaoDoMascote: Animation
    private lateinit var animacaoDoFormulario: Animation

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAnimation()

        hideKeyboard()
    }

    private fun initAnimation() {
        with(binding) {
            animacaoDoMascote = AnimationUtils.loadAnimation(this@LoginActivity, R.anim.frombottom2)
            animacaoDoFormulario = AnimationUtils.loadAnimation(this@LoginActivity,
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