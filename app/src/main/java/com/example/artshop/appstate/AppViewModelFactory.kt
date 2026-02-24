package com.example.artshop.appstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.artshop.di.AppContainer

class AppViewModelFactory(private val appContainer: AppContainer) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(appContainer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}