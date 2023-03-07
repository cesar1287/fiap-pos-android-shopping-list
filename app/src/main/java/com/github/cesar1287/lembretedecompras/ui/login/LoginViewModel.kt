package com.github.cesar1287.lembretedecompras.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.cesar1287.lembretedecompras.model.RequestState
import com.github.cesar1287.lembretedecompras.model.User
import com.github.cesar1287.lembretedecompras.repository.UserRepository

class LoginViewModel: ViewModel() {

    private val userRepository = UserRepository()

    val loginState = MutableLiveData<RequestState<String>>()

    fun login(user: User) {
        loginState.postValue(
            userRepository.login(user).value
        )
    }
}