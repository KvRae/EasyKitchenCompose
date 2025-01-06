package com.kvrae.easykitchen.presentation.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.User
import com.kvrae.easykitchen.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel()
{
    var LoginResponse: User? = null

    fun login(username: String, password: String) {
        viewModelScope.launch {
            LoginResponse = loginRepository.login(username, password)
        }

    }


}