package com.github.cesar1287.lembretedecompras.repository

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.cesar1287.lembretedecompras.model.RequestState
import com.github.cesar1287.lembretedecompras.model.User

class UserRepository(private val context: Context) {

    fun login(user: User): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()

        if (user.email == "compra@facil.com.br" &&
            user.password == "123456"
        ) {
            val pref = context.getSharedPreferences("lembretedecompras", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("email", user.email)
            editor.apply()
            response.value = RequestState.Success("")
        } else {
            response.value = RequestState.Error(Exception("Usuário ou senha invalido"))
        }
        return response
    }

    fun getLoggedUser(): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()

        val pref = context.getSharedPreferences("lembretedecompras", 0)
        val email = pref.getString("email", "") ?: ""
        response.value = RequestState.Success(email)

        return response
    }

}