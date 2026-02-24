package com.example.artshop.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artshop.di.AppContainer
import com.example.artshop.models.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PicturesViewModel(private val appContainer: AppContainer) : ViewModel() {
    private val repository = appContainer.artShopRepository

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadPhotos()
    }



    private fun loadPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _photos.value = repository.getPhotos()
            } catch (e: Exception) {
                _error.value = "Failed to load initial data: ${e.message}"
                Log.e("AppViewModel", "Error loading initial data", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

}