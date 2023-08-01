package com.example.inseminator.core.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inseminator.core.data.api.response.item.LoginItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SessionViewModel(private val sessionRepository: SessionRepository) : ViewModel() {
    fun getUser(): Flow<LoginItem> {
        return sessionRepository.getUser()
    }

    fun logout() {
        viewModelScope.launch {
            sessionRepository.logout()
        }
    }

    fun saveUser(user: LoginItem) {
        viewModelScope.launch {
            sessionRepository.saveUser(user)
        }
    }
}