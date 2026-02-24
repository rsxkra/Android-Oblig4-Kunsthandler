package com.example.artshop.appstate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artshop.di.AppContainer
import com.example.artshop.models.Artist
import com.example.artshop.models.Category
import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.Photo
import com.example.artshop.models.PhotoSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(appContainer: AppContainer): ViewModel() {
    private val _appState = MutableStateFlow(AppState())
    val appState: StateFlow<AppState> = _appState.asStateFlow()

    fun updateOrientation(newOrientation: Int) {
        _appState.update { currentState ->
            currentState.copy(orientation = newOrientation)
        }
    }
}