package com.github.cesar1287.lembretedecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cesar1287.lembretedecompras.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}