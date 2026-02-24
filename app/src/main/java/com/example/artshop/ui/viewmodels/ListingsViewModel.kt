package com.example.artshop.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artshop.di.AppContainer
import com.example.artshop.models.Artist
import com.example.artshop.models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListingsViewModel(private val appContainer: AppContainer) : ViewModel() {
    private val repository = appContainer.artShopRepository

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _artists = MutableStateFlow<List<Artist>>(emptyList())
    val artists: StateFlow<List<Artist>> = _artists.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadListings()
    }


    // Load Photo Details
    private fun loadListings() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _categories.value = repository.getCategories()
                _artists.value = repository.getArtists()
            } catch (e: Exception) {
                _error.value = "Failed to load listings: ${e.message}"
                Log.e("ListingsViewModel", "Error loading listings", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}