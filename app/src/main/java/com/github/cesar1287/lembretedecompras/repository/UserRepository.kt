package com.github.cesar1287.lembretedecompras.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.cesar1287.lembretedecompras.model.RequestState
import com.github.cesar1287.lembretedecompras.model.User

class UserRepository {

    fun login(user: User): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()

        if (user.email == "compra@facil.com.br" &&
            user.password == "123456"
        ) {
            response.value = RequestState.Success("")
        } else {
            response.value = RequestState.Error(Exception("Usuário ou senha invalido"))
        }

        return response
    }

}