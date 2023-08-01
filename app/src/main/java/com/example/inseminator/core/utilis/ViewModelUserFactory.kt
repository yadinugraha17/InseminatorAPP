package com.example.inseminator.core.utilis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inseminator.core.session.SessionRepository
import com.example.inseminator.core.session.SessionViewModel

class ViewModelUserFactory(private val pref: SessionRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SessionViewModel::class.java) -> {
                SessionViewModel(pref) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
}