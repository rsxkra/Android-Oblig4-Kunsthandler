package com.example.artshop.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.artshop.di.AppContainer

class PicturesViewModelFactory(private val appContainer: AppContainer) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PicturesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PicturesViewModel(appContainer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}