package com.example.artshop.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.artshop.di.AppContainer

class DetailsViewModelFactory(private val appContainer: AppContainer) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(appContainer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}