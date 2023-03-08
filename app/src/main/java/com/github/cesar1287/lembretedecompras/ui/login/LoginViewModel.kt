package com.github.cesar1287.lembretedecompras.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.cesar1287.lembretedecompras.model.RequestState
import com.github.cesar1287.lembretedecompras.model.User
import com.github.cesar1287.lembretedecompras.repository.UserRepository

class LoginViewModel(
    application: Application
): AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    val loginState = MutableLiveData<RequestState<String>>()

    val loggedUserState = MutableLiveData<RequestState<String>>()

    fun login(user: User) {
        loginState.postValue(
            userRepository.login(user).value
        )
    }

    fun getLoggedUser() {
        loggedUserState.value = userRepository.getLoggedUser().value
    }

}